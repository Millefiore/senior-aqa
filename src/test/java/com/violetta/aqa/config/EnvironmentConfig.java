package com.violetta.aqa.config;

import lombok.experimental.FieldDefaults;
import org.aeonbits.owner.ConfigFactory;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EnvironmentConfig {
    static {
        String env = System.getProperty("env", "dev");
        System.setProperty("env", env);
    }

    public static final ServerConfig CONFIG = ConfigFactory.create(ServerConfig.class);

}
