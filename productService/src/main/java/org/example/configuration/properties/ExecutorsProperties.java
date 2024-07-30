
package org.example.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Profile;

@Getter
@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorsProperties {

    private final RestTemplateProperties paymentsExecutorClient;

    @ConstructorBinding
    public ExecutorsProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }
}
