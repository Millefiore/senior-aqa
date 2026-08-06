package com.violetta.aqa.client;

import com.violetta.aqa.client.util.ApiConstants;
import com.violetta.aqa.config.EnvironmentConfig;
import com.violetta.aqa.dto.user.response.UserPageDto;
import io.restassured.response.ValidatableResponse;
import lombok.experimental.FieldDefaults;

import static io.restassured.RestAssured.given;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserApiClient extends BaseApiClient {

    public UserPageDto getUsersPage(int page) {
        return getUsersPage(page, 200)
                .extract()
                .as(UserPageDto.class);
    }

    public ValidatableResponse getUsersPage(int page, int code) {
        return given()
                    .spec(requestSpec(EnvironmentConfig.CONFIG.userServiceUrl(), EnvironmentConfig.CONFIG.apiKey()))
                    .queryParam("page", page)
                .when()
                    .get(ApiConstants.USER_API.getUrl())
                .then()
                    .spec(responseSpec(code));
    }

}
