package com.example.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePublisherRequest {

  private String name;

  private String address;

  private String website;
}
