package com.example.core.http;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class InternalHttpClientLoggingInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    log.info("--> {} {}", request.getMethod(), request.getURI());
    ClientHttpResponse response = execution.execute(request, body);
    log.info(
        "<-- {} {} | status: {}", request.getMethod(), request.getURI(), response.getStatusCode());

    return response;
  }
}
