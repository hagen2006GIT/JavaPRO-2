package org.example.configuration;

import lombok.Getter;
import org.example.configuration.properties.ExecutorsProperties;
import org.example.configuration.properties.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({
        ExecutorsProperties.class,
})
@Getter
public class ApplicationConfig {

    @Bean
    public RestTemplate executorRestClient(
            ExecutorsProperties executorsProperties,
            org.example.configuration.RestTemplateResponseErrorHandler errorHandler
    ) {
        RestTemplateProperties executorClient = executorsProperties.getPaymentsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(executorClient.getUrl())
                .setConnectTimeout(executorClient.getConnectTimeout())
                .setReadTimeout(executorClient.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
