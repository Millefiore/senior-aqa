package com.violetta.aqa.test.auth;

import com.violetta.aqa.client.auth.AuthApiClient;
import com.violetta.aqa.dto.auth.request.LoginDto;
import com.violetta.aqa.test.auth.dataprovider.InvalidLoginProvider;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import io.testomat.core.annotation.Title;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.hamcrest.Matchers.*;

@Tags({@Tag("api"), @Tag("auth")})
@Issue("KAN-1")
@Epic("Auth API")
@Feature("Auth Operations")
@Severity(SeverityLevel.NORMAL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthApiTest {

    AuthApiClient authApiClient = new AuthApiClient();

    @Test
    @Title("Login Successful")
    @Story("Login Valid Creds")
    @DisplayName("Login with Valid email and password and get 200 and token")
    public void login_validEmailValidPassword_return200AndToken() {
        ValidatableResponse validatableResponse = authApiClient.loginUser(LoginDto.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build(), 200);
        Allure.step("Verify login response", () -> {
            validatableResponse
                    .body("token", allOf(notNullValue(), not(emptyString())));
        });
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidLoginProvider.class)
    @Title("Login Failure")
    @Story("Login Invalid : Email and Password")
    @DisplayName("Login with Invalid email and password and get 400 and error message")
    public void login_invalidEmailInvalidPassword_return200AndErrorMessage(LoginDto loginDto) {
        ValidatableResponse validatableResponse = authApiClient.loginUser(loginDto, 400);
        Allure.step("Verify login response", () -> {
            validatableResponse
                    .body("error", containsString("user not found"));
        });
    }


    @ParameterizedTest
    @ArgumentsSource(InvalidLoginProvider.class)
    @Title("Login Failure")
    @Story("Login Invalid : Email and Password Null")
    @DisplayName("Login with Missing password and get 400 and error message")
    public void login_EmailOnly_return400AndErrorMessage(LoginDto loginDto) {
        loginDto.setPassword(null);
        ValidatableResponse validatableResponse = authApiClient.loginUser(loginDto, 400);
        Allure.step("Verify login response", () -> {
            validatableResponse
                    .body("error", containsString("Missing password"));
        });
    }

}
