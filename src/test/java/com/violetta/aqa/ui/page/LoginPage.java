package com.violetta.aqa.ui.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.violetta.aqa.config.EnvironmentConfig;
import com.violetta.aqa.ui.dto.user.request.LoginDto;
import io.qameta.allure.Step;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LoginPage extends BasePage {
    Locator usernameInput;
    Locator passwordInput;
    Locator loginButton;
    Locator usernameVariants;
    Locator passwordVariants;
    Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        usernameInput = page.getByTestId("username");
        passwordInput = page.getByPlaceholder("Password");
        loginButton = page.locator("#login-button");
        errorMessage = page.locator("div .error h3");
        usernameVariants = page.locator("div [id='login_credentials']");
        passwordVariants = page.locator("div .login_password");
    }

    @Step("Open login page")
    public LoginPage open() {
        page.navigate(EnvironmentConfig.CONFIG.baseUiUrl());
        return this;
    }

    @Step("Login with valid credentials")
    public InventoryPage loginValid(LoginDto loginDto) {
        fillForm(loginDto);
        return new InventoryPage(page);
    }

    public LoginPage loginInvalid(LoginDto loginDto) {
        fillForm(loginDto);
        return this;
    }

    public String getRandomUsernameVariant() {
//        String textContent = usernameVariants.innerText();
//        String[] usernames = textContent.split("\n");
//        return usernames[new Random().ints(1, usernames.length - 1).findFirst().getAsInt()];
        return "standard_user";
    }

    public String getRandomPasswordVariant() {
        String textContent = passwordVariants.innerText();
        String[] passwords = textContent.split("\n");
        return passwords[1];
    }

    @Step("Fill login form with username {0} and password {1}")
    private void fillForm(LoginDto loginDto) {
        usernameInput.fill(loginDto.email());
        passwordInput.fill(loginDto.password());
        loginButton.click();
    }
}
