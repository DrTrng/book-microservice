package com.example.order.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

  private UUID id;

  private String title;

  private String description;

  private String isbn;

  private BigDecimal price;

  private LocalDate publishedDate;

  private String language;

  private Integer pageCount;

  private PublisherResponse publisher;

  private List<AuthorResponse> authors;

  private List<CategoryResponse> categories;
}
