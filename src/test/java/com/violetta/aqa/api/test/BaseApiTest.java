package com.violetta.aqa.api.test;

import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected Faker faker;

    @BeforeAll
    public static void globalSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.config = RestAssured.config()
                .httpClient(RestAssured.config().getHttpClientConfig().reuseHttpClientInstance());
    }

    @BeforeEach
    public void setUpTest() {
        this.faker = new Faker();
    }

}
