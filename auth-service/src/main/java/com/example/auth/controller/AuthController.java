package com.example.auth.controller;

import com.example.auth.constant.AuthApiPaths;
import com.example.auth.model.auth.CreateUserRequest;
import com.example.auth.model.auth.CreateUserResponse;
import com.example.auth.service.AuthService;
import com.example.core.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthApiPaths.AUTH_BASE)
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @Operation(
      summary = "Register a new user",
      description = "Creates a new user account and returns the created user details")
  @ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "201",
        description = "User registered successfully",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CreateUserResponse.class)))
  })
  @PostMapping(AuthApiPaths.REGISTER)
  public ResponseEntity<ApiResponse<CreateUserResponse>> registerUser(
      @RequestBody CreateUserRequest createUserRequest) {

    ApiResponse<CreateUserResponse> response =
        ApiResponse.of(HttpStatus.CREATED, authService.registerUser(createUserRequest));

    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
