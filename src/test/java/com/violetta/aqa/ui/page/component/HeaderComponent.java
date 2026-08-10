package com.violetta.aqa.ui.page.component;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.violetta.aqa.ui.page.CartPage;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HeaderComponent extends BaseComponent {
    Locator cartIcon;
    Locator cartNumber;
    Locator titleText;
    Locator menuButton;

    public HeaderComponent(Page page) {
        super(page);
        cartIcon = page.getByTestId("shopping-cart-link");
        titleText = page.locator("div.header_secondary_container > span.title");
        menuButton = page.locator("div.bm-burger-button").getByRole(AriaRole.BUTTON);
        cartNumber = page.getByTestId("shopping-cart-badge");
    }

    public Locator title() {
        return titleText;
    }

    public Locator getCart() {
        return cartIcon;
    }

    public CartPage cartClick() {
        cartIcon.click();
        return new CartPage(page);
    }

    public SidebarComponent menuClick() {
        menuButton.click();
        return new SidebarComponent(page);
    }

}
