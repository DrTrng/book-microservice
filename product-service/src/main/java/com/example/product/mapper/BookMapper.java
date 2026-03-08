package com.example.product.mapper;

import com.example.product.entity.Book;
import com.example.product.model.BookResponse;
import com.example.product.model.CreateBookRequest;

public class BookMapper {

  public static BookResponse toResponse(Book book) {

    return BookResponse.builder()
        .id(book.getId())
        .title(book.getTitle())
        .description(book.getDescription())
        .isbn(book.getIsbn())
        .price(book.getPrice())
        .publishedDate(book.getPublishedDate())
        .language(book.getLanguage())
        .pageCount(book.getPageCount())
        .build();
  }

  public static Book toEntity(CreateBookRequest request) {

    Book book = new Book();

    book.setTitle(request.getTitle());
    book.setIsbn(request.getIsbn());
    book.setPrice(request.getPrice());
    book.setDescription(request.getDescription());
    book.setPublishedDate(request.getPublishedDate());
    book.setLanguage(request.getLanguage());
    book.setPageCount(request.getPageCount());

    return book;
  }
}
