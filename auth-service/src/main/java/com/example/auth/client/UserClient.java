package com.example.auth.client;

import com.example.auth.constant.AuthApiPath;
import com.example.auth.model.auth.CreateUserRequest;
import com.example.auth.model.auth.CreateUserResponse;
import com.example.core.http.InternalHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClient {

  private final InternalHttpClient httpClient;
  private final ServicesProperties services;

  public CreateUserResponse createUser(CreateUserRequest request) {
    return httpClient.post(
        services.getUserUrl() + AuthApiPath.USER_CLIENT, request, CreateUserResponse.class);
  }
}
