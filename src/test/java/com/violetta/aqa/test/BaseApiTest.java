package com.violetta.aqa.test;

import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected Faker faker;

    @BeforeAll
    public static void globalSetup() {
        // 1. Автоматически печатать запрос и ответ в консоль, если ассерт в тесте УПАЛ
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // 2. Настройка таймаутов соединения для всех сервисов по умолчанию
        RestAssured.config = RestAssured.config()
                .httpClient(RestAssured.config().getHttpClientConfig().reuseHttpClientInstance());
    }

    @BeforeEach
    public void setUpTest() {
        this.faker = new Faker();
    }

}
