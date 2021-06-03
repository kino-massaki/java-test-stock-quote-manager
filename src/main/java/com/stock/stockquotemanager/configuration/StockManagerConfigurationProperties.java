package com.stock.stockquotemanager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "stock-manager")
public class StockManagerConfigurationProperties {
    private String baseUrl;

    public StockManagerConfigurationProperties() {
    }

    public StockManagerConfigurationProperties(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
