package com.example.product.controller;

import com.example.product.entity.Author;
import com.example.product.model.CreateAuthorRequest;
import com.example.product.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @PostMapping
  public Author createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {

    return authorService.createAuthor(createAuthorRequest);
  }
}
