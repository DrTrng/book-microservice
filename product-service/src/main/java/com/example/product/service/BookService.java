package com.example.product.service;

import com.example.product.model.BookResponse;
import com.example.product.model.CreateBookRequest;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

  BookResponse createBook(CreateBookRequest request);

  BookResponse getBook(UUID id);

  BookResponse updateBook(UUID id, CreateBookRequest request);

  void deleteBook(UUID id);

  Page<BookResponse> getBooks(Pageable pageable);
}
