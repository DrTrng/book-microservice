package com.example.core.http;

import com.example.core.exception.RemoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class InternalHttpClient {

  private final RestClient restClient;
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public <T> T get(String url, Class<T> responseType) {
    return restClient
        .get()
        .uri(url)
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .body(responseType);
  }

  public <T> T post(String url, Object body, Class<T> responseType) {
    return restClient
        .post()
        .uri(url)
        .body(body)
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .body(responseType);
  }

  public <T> T put(String url, Object body, Class<T> responseType) {
    return restClient
        .put()
        .uri(url)
        .body(body)
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .body(responseType);
  }

  public <T> T patch(String url, Object body, Class<T> responseType) {
    return restClient
        .patch()
        .uri(url)
        .body(body)
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .body(responseType);
  }

  public void delete(String url) {
    restClient
        .delete()
        .uri(url)
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .toBodilessEntity();
  }

  private void handleError(HttpRequest request, ClientHttpResponse response) throws IOException {
    String body = new String(response.getBody().readAllBytes());
    try {
      JsonNode error = OBJECT_MAPPER.readTree(body);
      int status = error.path("status").asInt(response.getStatusCode().value());
      String errorCode = error.path("errorCode").asText("REMOTE_ERROR");
      String message = error.path("message").asText(body);
      throw new RemoteServiceException(status, errorCode, message);

    } catch (JsonProcessingException e) {
      throw new RemoteServiceException(response.getStatusCode().value(), "REMOTE_ERROR", body);
    }
  }
}
