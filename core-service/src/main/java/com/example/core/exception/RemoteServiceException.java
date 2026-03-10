package com.example.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RemoteServiceException extends RuntimeException {
  private final int status;
  private final String errorCode;
  private final String message;
}
