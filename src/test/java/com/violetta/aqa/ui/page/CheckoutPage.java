package com.violetta.aqa.ui.page;

import com.microsoft.playwright.Page;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CheckoutPage extends BasePage {

    public CheckoutPage(Page page) {
        super(page);
    }

}
