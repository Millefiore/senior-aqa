package com.violetta.aqa.ui.test;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseUiTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(isHeadless)
        );
    }

    @AfterAll
    public static void closeBrowser() {
        Optional.ofNullable(playwright).ifPresent(Playwright::close);
    }

    @BeforeEach
    public void createContextAndPage() {
        playwright = Playwright.create();

        playwright.selectors().setTestIdAttribute("data-test");

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("target/videos/")));

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        Path tracePath = Paths.get("target/traces/trace-" + UUID.randomUUID() + ".zip");

        try {
            if (page != null && !page.isClosed()) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                Allure.addAttachment("Final Page Screenshot", "image/png",
                        new java.io.ByteArrayInputStream(screenshot), ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
            if (Files.exists(tracePath)) {
                try (InputStream is = Files.newInputStream(tracePath)) {
                    Allure.addAttachment("Playwright Trace", "application/zip", is, ".zip");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Path videoPath = page.video() != null ? page.video().path() : null;

        context.close();

        if (videoPath != null && Files.exists(videoPath)) {
            try (InputStream is = Files.newInputStream(videoPath)) {
                Allure.addAttachment("Execution Video", "video/webm", is, ".webm");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

}
