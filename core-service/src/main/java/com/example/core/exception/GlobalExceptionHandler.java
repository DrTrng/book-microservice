package com.example.core.exception;

import com.example.core.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final MessageSource messageSource;
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @ExceptionHandler(BookBaseException.class)
  public ResponseEntity<ApiResponse<Void>> handleBaseException(BookBaseException ex) {
    String message =
        messageSource.getMessage(
            ex.getErrorCode().getMessageKey(), ex.getArgs(), LocaleContextHolder.getLocale());

    return ResponseEntity.status(ex.getErrorCode().getStatus())
        .body(ApiResponse.error(ex.getErrorCode(), message));
  }

  @ExceptionHandler(RemoteServiceException.class)
  public ResponseEntity<ApiResponse<Void>> handleRemoteServiceException(RemoteServiceException ex) {
    return ResponseEntity.status(ex.getStatus())
        .body(ApiResponse.error(ex.getStatus(), ex.getErrorCode(), ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
    return ResponseEntity.status(500)
        .body(
            ApiResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessageConstant.INTERNAL_ERROR));
  }
}
