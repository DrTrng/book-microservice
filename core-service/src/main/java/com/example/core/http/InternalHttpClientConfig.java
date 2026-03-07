package com.example.core.http;

import java.net.http.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(InternalHttpClientProperties.class)
public class InternalHttpClientConfig {
  private final InternalHttpClientProperties properties;

  @Bean
  public RestClient restClient() {
    HttpClient httpClient =
        HttpClient.newBuilder().connectTimeout(properties.getConnectTimeout()).build();

    JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);

    requestFactory.setReadTimeout(properties.getReadTimeout());

    return RestClient.builder()
        .requestFactory(requestFactory)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
