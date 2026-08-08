package com.violetta.aqa.client.auth;

import com.violetta.aqa.client.BaseApiClient;
import com.violetta.aqa.client.util.ApiConstants;
import com.violetta.aqa.config.EnvironmentConfig;
import com.violetta.aqa.dto.auth.request.LoginDto;
import com.violetta.aqa.dto.user.response.UserPageDto;
import com.violetta.aqa.dto.user.response.UserSingleDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.experimental.FieldDefaults;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthApiClient extends BaseApiClient {

    @Step("Login user: user = {loginDto}")
    public ValidatableResponse loginUser(LoginDto loginDto, int code) {
        return given()
                .spec(requestSpec(EnvironmentConfig.CONFIG.serviceUrl(), EnvironmentConfig.CONFIG.apiKey()))
                .body(loginDto)
                .when()
                .post(ApiConstants.LOGIN_API.getUrl())
                .then()
                .spec(responseSpec(code));
    }

}
