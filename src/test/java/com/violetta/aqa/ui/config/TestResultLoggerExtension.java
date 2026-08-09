package com.violetta.aqa.ui.config;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;

public class TestResultLoggerExtension implements TestWatcher {

    private final Page page;

    public TestResultLoggerExtension(Page page) {
        this.page = page;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshot), ".png");
        }
    }
}