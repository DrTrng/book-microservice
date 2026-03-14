package com.example.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.core.exception.BookBaseException;
import com.example.user.entity.User;
import com.example.user.exception.UserException;
import com.example.user.model.user.UserRequest;
import com.example.user.model.user.UserResponse;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new BookBaseException(UserException.DUPLICATE_EMAIL);
    }
    User user =
        User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

    User saved = userRepository.save(user);
    return UserResponse.builder().username(saved.getUsername()).email(saved.getEmail()).build();
  }

  public List<UserResponse> getAll() {
    return userRepository.findAll().stream()
        .map(
            user ->
                UserResponse.builder().username(user.getUsername()).email(user.getEmail()).build())
        .toList();
  }

  public UserResponse getById(UUID id) {
    User user = userRepository.findById(id).orElseThrow();
    return UserResponse.builder().username(user.getUsername()).email(user.getEmail()).build();
  }

  public void delete(UUID id) {
    userRepository.deleteById(id);
  }
}
