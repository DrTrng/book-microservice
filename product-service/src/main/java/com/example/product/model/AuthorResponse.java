package com.example.product.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {

  private UUID id;

  private String name;

  private String biography;
}
