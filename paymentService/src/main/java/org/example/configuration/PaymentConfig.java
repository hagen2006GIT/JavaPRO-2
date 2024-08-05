package org.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {

    @Value("${integrations.services.products-service.url}")
    private String productsServiceUrl;

    @Bean
    public RestTemplate productRestClient() {
        return new RestTemplateBuilder()
                .rootUri(productsServiceUrl)
                .build();
    }
}