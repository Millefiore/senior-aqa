package com.violetta.aqa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${env}.properties", "classpath:config/stage.properties"})
public interface ServerConfig extends Config {

    @Key("user.service.url")
    @DefaultValue("https://reqres.in")
    String serviceUrl();

    @Key("ui.service.url")
    @DefaultValue("https://www.saucedemo.com")
    String baseUiUrl();

    @Key("timeout")
    @DefaultValue("5000")
    int timeout();

    @Key("api.key")
    String apiKey();

}
