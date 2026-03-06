package com.example.core.http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InternalHttpClient {

    private final RestClient restClient;

    public <T> T get(String url, Class<T> responseType) {
        return restClient
                .get()
                .uri(url)
                .retrieve()
                .body(responseType);
    }

    public <T> T post(String url, Object body, Class<T> responseType) {
        return restClient
                .post()
                .uri(url)
                .body(body)
                .retrieve()
                .body(responseType);
    }

    public <T> T put(String url, Object body, Class<T> responseType) {
        return restClient
                .put()
                .uri(url)
                .body(body)
                .retrieve()
                .body(responseType);
    }

    public void delete(String url) {
        restClient
                .delete()
                .uri(url)
                .retrieve()
                .toBodilessEntity();
    }
}