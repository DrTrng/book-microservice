package com.example.core.dto;

import com.example.core.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
  private int status;
  private String errorCode;
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

  public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
    return ApiResponse.<T>builder()
        .status(errorCode.getStatus())
        .errorCode(errorCode.getMessageKey())
        .message(message)
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> error(int status, String errorCode, String message) {
    return ApiResponse.<T>builder()
        .status(status)
        .errorCode(errorCode)
        .message(message)
        .timestamp(LocalDateTime.now())
        .build();
  }
}
