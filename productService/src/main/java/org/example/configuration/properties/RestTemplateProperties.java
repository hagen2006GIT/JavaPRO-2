package org.example.configuration.properties;


import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Setter
@Getter
public class RestTemplateProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;

}
