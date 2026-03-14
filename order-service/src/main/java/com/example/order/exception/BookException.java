package com.example.order.exception;

import com.example.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum BookException implements ErrorCode {
  BOOK_NOT_EXIST(400, "20001");

  private final int status;
  private final String messageKey;

  BookException(int status, String messageKey) {
    this.status = status;
    this.messageKey = messageKey;
  }
}
