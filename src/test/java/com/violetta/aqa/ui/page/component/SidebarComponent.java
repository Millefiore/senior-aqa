package com.violetta.aqa.ui.page.component;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.violetta.aqa.ui.page.LoginPage;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SidebarComponent extends BaseComponent {
    Locator logoutButton;
    Locator closeButton;

    public SidebarComponent(Page page) {
        super(page);
        logoutButton = page.locator("div.bm-menu > nav").getByRole(AriaRole.LINK).getByText("Logout");
        closeButton = page.locator("#react-burger-cross-btn");
    }

    public Locator getLogout() {
        return logoutButton;
    }

    public LoginPage logoutClick() {
        logoutButton.click();
        return new LoginPage(page);
    }

    public void closeClick() {
        closeButton.click();
    }

}
