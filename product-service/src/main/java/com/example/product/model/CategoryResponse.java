package com.example.product.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {

  private UUID id;

  private String name;

  private String description;
}
