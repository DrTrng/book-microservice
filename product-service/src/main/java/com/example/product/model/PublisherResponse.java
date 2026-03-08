package com.example.product.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PublisherResponse {

  private UUID id;

  private String name;

  private String address;

  private String website;
}
