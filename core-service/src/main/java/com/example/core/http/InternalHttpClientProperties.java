package com.example.core.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

import static com.example.core.http.InternalHttpClientConstant.DEFAULT_CONNECT_TIMEOUT;
import static com.example.core.http.InternalHttpClientConstant.DEFAULT_READ_TIMEOUT;

@ConfigurationProperties(prefix = "internal.http")
@Getter
@Setter
public class InternalHttpClientProperties {
    private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private Duration readTimeout = DEFAULT_READ_TIMEOUT;
}
