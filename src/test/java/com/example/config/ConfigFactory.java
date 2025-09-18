package com.example.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                PROPS.load(is);
            } else {
                throw new RuntimeException("config.properties not found in resources");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPS.getProperty(key, defaultValue));
    }
}
