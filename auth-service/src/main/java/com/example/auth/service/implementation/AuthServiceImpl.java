package com.example.auth.service.implementation;

import com.example.auth.client.UserClient;
import com.example.auth.model.auth.CreateUserRequest;
import com.example.auth.model.auth.CreateUserResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserClient userClient;

  @Override
  public CreateUserResponse registerUser(CreateUserRequest request) {
    return userClient.createUser(request);
  }
}
