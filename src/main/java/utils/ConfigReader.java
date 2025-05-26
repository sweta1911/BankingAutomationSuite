package utils;

import customExceptions.CustomRunTimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;
    private ConfigReader() {
        throw new IllegalStateException("ConfigReader class");
    }
    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new CustomRunTimeException("Could not load config.properties");
        }
    }
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
