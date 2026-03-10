package com.example.core.exception;

import lombok.Getter;

@Getter
public class BookBaseException extends RuntimeException {
  private final ErrorCode errorCode;
  private final Object[] args;

  public BookBaseException(ErrorCode errorCode, Object... args) {
    super(errorCode.getMessageKey());
    this.errorCode = errorCode;
    this.args = args;
  }
}
