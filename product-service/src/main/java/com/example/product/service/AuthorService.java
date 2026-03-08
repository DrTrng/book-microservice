package com.example.product.service;

import com.example.product.entity.Author;
import com.example.product.model.CreateAuthorRequest;

public interface AuthorService {

  Author createAuthor(CreateAuthorRequest createAuthorRequest);
}
