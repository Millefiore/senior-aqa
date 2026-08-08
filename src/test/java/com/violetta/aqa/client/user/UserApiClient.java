package com.violetta.aqa.client.user;

import com.violetta.aqa.client.BaseApiClient;
import com.violetta.aqa.client.util.ApiConstants;
import com.violetta.aqa.config.EnvironmentConfig;
import com.violetta.aqa.dto.user.request.UserDto;
import com.violetta.aqa.dto.user.response.UserPageDto;
import com.violetta.aqa.dto.user.response.UserSingleDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.experimental.FieldDefaults;

import static io.restassured.RestAssured.given;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserApiClient extends BaseApiClient {

    @Step("Get users pageable: page = {page}")
    public UserPageDto getUsersPage(int page) {
        return getUsersPage(page, 200)
                .extract()
                .as(UserPageDto.class);
    }

    @Step("Create user: user = {userDto}")
    public UserDto createUser(UserDto userDto) {
        return given()
                .spec(requestSpec(EnvironmentConfig.CONFIG.serviceUrl(), EnvironmentConfig.CONFIG.apiKey()))
                .body(userDto)
                .when()
                .post(ApiConstants.USER_API.getUrl())
                .then()
                .spec(responseSpec(201))
                .extract()
                .as(UserDto.class);
    }

    @Step("Get user by id: user id = {id}")
    public UserSingleDto getUser(int id) {
        return given()
                .spec(requestSpec(EnvironmentConfig.CONFIG.serviceUrl(), EnvironmentConfig.CONFIG.apiKey()))
                .pathParam("id", id)
                .when()
                .get(ApiConstants.USER_API.getUrl() + "/{id}")
                .then()
                .spec(responseSpec(200))
                .extract()
                .as(UserSingleDto.class);
    }

    public ValidatableResponse getUsersPage(int page, int code) {
        return given()
                .spec(requestSpec(EnvironmentConfig.CONFIG.serviceUrl(), EnvironmentConfig.CONFIG.apiKey()))
                .queryParam("page", page)
                .when()
                .get(ApiConstants.USER_API.getUrl())
                .then()
                .spec(responseSpec(code));
    }

}
