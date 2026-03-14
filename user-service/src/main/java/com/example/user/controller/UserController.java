package com.example.user.controller;

import com.example.core.dto.ApiResponse;
import com.example.user.constant.UserApiPath;
import com.example.user.constant.UserMessage;
import com.example.user.model.user.UserRequest;
import com.example.user.model.user.UserResponse;
import com.example.user.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserApiPath.USER_BASE)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<ApiResponse<UserResponse>> create(@RequestBody UserRequest request) {

    ApiResponse<UserResponse> response =
        ApiResponse.of(HttpStatus.CREATED, userService.createUser(request));
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping
  public ApiResponse<List<UserResponse>> getAll() {
    return ApiResponse.of(HttpStatus.OK, userService.getAll());
  }

  @GetMapping("/{id}")
  public ApiResponse<UserResponse> getById(@PathVariable UUID id) {
    return ApiResponse.of(HttpStatus.OK, userService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
    userService.delete(id);
    return ResponseEntity.ok(ApiResponse.of(HttpStatus.NO_CONTENT, UserMessage.USER_DELETED));
  }
}
