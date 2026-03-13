package com.example.product.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.model.BookResponse;
import com.example.product.model.CreateBookRequest;
import com.example.product.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping
  public BookResponse createBook(@RequestBody CreateBookRequest request) {
    return bookService.createBook(request);
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable("id") UUID id) {
    return bookService.getBook(id);
  }

  @GetMapping
  public Page<BookResponse> getBooks(Pageable pageable) {
    return bookService.getBooks(pageable);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable("id") UUID id) {
    bookService.deleteBook(id);
  }
}
