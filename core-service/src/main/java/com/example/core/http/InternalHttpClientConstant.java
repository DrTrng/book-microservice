package com.example.core.http;

import java.time.Duration;

public class InternalHttpClientConstant {
    private InternalHttpClientConstant() {}

    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(2);
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(5);
}
