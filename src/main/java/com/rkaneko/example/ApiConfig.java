package com.rkaneko.example;

import okhttp3.logging.HttpLoggingInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.rkaneko.example.util.api.ApiRegistry;

@PropertySource("classpath:api-config.properties")
public class ApiConfig {
    @Value("${service.baseUrl}")
    private String baseUrl;
    @Value("${service.log.level}")
    private String logLevel;

    @Bean
    public ApiRegistry apiRegistry() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.valueOf(logLevel);
        return new ApiRegistry(baseUrl, level);
    }
}
