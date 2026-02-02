package com.tesis.teamsoft.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "ext-modules.combination-mod")
@Component
@Getter
@Setter
public class CombinationModApiConfig {

    // General Config
    private String baseUrl;
    private String defaultKey;
    private int timeoutSeconds = 30;
    private int maxRetries = 3;

    // Specific Configs for every service
    private Map<String, ServiceConfig> services = new HashMap<>();

    // Child class for every service
    @Getter
    @Setter
    public static class ServiceConfig {
        private String endpoint;
        private String apiKey;
        private boolean enabled = true;
        private int cacheTimeoutMinutes = 5;
    }

    // Helper method
    public ServiceConfig getServiceConfig(String serviceName) {
        return services.getOrDefault(serviceName, new ServiceConfig());
    }
}