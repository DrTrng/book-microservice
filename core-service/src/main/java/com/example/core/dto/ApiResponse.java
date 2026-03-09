package com.example.core.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
  private int status;
  private String message;
  private T data;
  private LocalDateTime timestamp;

  public static <T> ApiResponse<T> of(HttpStatus status, T data, String message) {
    return ApiResponse.<T>builder()
        .status(status.value())
        .data(data)
        .message(message)
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> of(HttpStatus status, T data) {
    return of(status, data, null);
  }

  public static <T> ApiResponse<T> of(HttpStatus status, String message) {
    return of(status, null, message);
  }
}
