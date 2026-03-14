package com.example.order.dto.book;

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
