package com.example.product.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

  private String name;

  private String description;

  private UUID parentId;
}
