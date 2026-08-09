package com.violetta.aqa.api.client;

import com.violetta.aqa.api.config.ApiSpec;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BaseApiClient {

    protected RequestSpecification requestSpec(String url, String apiKey) {
        return ApiSpec.requestSpec(url, apiKey);
    }

    protected RequestSpecification requestSpec(String url) {
        return ApiSpec.requestSpec(url);
    }

    protected ResponseSpecification responseSpec(int code) {
        return ApiSpec.responseSpec(code);
    }

}
