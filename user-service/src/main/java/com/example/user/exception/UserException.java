package com.example.user.exception;

import com.example.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum UserException implements ErrorCode {

    DUPLICATE_EMAIL(409, "54091"),
    USER_NOT_FOUND(404, "54041");

    private final int status;
    private final String messageKey;

    UserException(int status, String messageKey) {
        this.status = status;
        this.messageKey = messageKey;
    }
}