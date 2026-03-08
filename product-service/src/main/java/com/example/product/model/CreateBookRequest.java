package com.example.product.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequest {

  private String title;

  private String description;

  private String isbn;

  private BigDecimal price;

  private LocalDate publishedDate;

  private String language;

  private Integer pageCount;

  private UUID publisherId;

  private List<UUID> authorIds;

  private List<UUID> categoryIds;
}
