package com.violetta.aqa.ui.config;

public enum UiConstants {
    TEST_USER_EMAIL("standard_user"),
    TEST_USER_PASSWORD("secret_sauce");

    private final String value;

    UiConstants(String value) {
        this.value = value;
    }

    public String getUrl() {
        return value;
    }
}
