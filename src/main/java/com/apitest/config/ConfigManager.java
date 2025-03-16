package com.apitest.config;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                logger.error("config.properties file not found in the classpath");
                throw new RuntimeException("config.properties file not found in the classpath");
            }
        } catch (IOException e) {
            logger.error("Error loading properties file", e);
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public String getBaseUrl() {
        return getString("base.url");
    }

    public String getEndpoint(String endpointKey) {
        return getString("endpoint." + endpointKey);
    }
} 