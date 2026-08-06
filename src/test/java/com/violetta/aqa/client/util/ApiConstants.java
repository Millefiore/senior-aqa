package com.violetta.aqa.client.util;

public enum ApiConstants {
    USER_API("/api/users");

    private final String url;

    ApiConstants(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
