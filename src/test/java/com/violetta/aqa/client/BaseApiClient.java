package com.violetta.aqa.client;

import com.violetta.aqa.config.ApiSpec;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
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
