package com.violetta.aqa.ui.page.component;

import com.microsoft.playwright.Page;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = lombok.AccessLevel.PROTECTED, makeFinal = true)
public class BaseComponent {
    Page page;

    public BaseComponent(Page page) {
        this.page = page;
    }

}
