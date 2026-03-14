package com.example.user.service;

import com.example.user.model.user.UserRequest;
import com.example.user.model.user.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {
  UserResponse createUser(UserRequest request);

  List<UserResponse> getAll();

  UserResponse getById(UUID id);

  void delete(UUID id);
}
