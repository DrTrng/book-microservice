package com.example.auth.constant;

import com.example.core.apipath.ApiPaths;

public class AuthApiPaths {
  private AuthApiPaths() {}

  private static final String VERSION = "/v1"; // use when need for versioning arises

  public static final String AUTH_BASE = ApiPaths.API + ApiPaths.AUTH;
  public static final String REGISTER = "/register";

  public static final String USER_CLIENT = ApiPaths.API + ApiPaths.USER;
}
