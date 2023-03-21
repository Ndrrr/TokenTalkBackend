package com.example.apigateway.config;

import com.example.apigateway.client.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        var builder = new RestTemplateBuilder();
        return builder.errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

}
