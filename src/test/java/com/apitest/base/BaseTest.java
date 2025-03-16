package com.apitest.base;

import com.apitest.clients.RestClient;
import com.apitest.config.ConfigManager;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeClass;

@Log4j2
public class BaseTest {
    protected RestClient restClient;
    protected ConfigManager config;

    @BeforeClass
    public void setUp() {
        config = ConfigManager.getInstance();
        String baseUrl = config.getBaseUrl();
        restClient = new RestClient(baseUrl);
        log.info("Test setup completed with base URL: {}", baseUrl);
    }
} 