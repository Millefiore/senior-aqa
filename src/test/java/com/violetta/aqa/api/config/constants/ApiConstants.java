package com.violetta.aqa.api.config.constants;

public enum ApiConstants {
    USER_API("/api/users"),
    LOGIN_API("/api/login");

    private final String url;

    ApiConstants(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
