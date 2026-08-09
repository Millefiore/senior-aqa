package com.violetta.aqa.ui.page;

import com.microsoft.playwright.Page;
import com.violetta.aqa.ui.page.component.HeaderComponent;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class BasePage {
    Page page;
    HeaderComponent header;

    public BasePage(Page page) {
        this.page = page;
        header = new HeaderComponent(page);
    }

    @Step("Get header component")
    public HeaderComponent header() {
        return header;
    }

    public String getUrl() {
        return page.url();
    }

    public String getTabTitle() {
        return page.title();
    }

}
