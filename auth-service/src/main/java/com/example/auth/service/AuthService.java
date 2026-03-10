package com.example.auth.service;

import com.example.auth.model.auth.CreateUserRequest;
import com.example.auth.model.auth.CreateUserResponse;

public interface AuthService {
  CreateUserResponse registerUser(CreateUserRequest createUserRequest);
}
