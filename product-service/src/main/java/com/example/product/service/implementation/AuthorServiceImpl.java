package com.example.product.service.implementation;

import com.example.product.entity.Author;
import com.example.product.model.CreateAuthorRequest;
import com.example.product.repository.AuthorRepository;
import com.example.product.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Author createAuthor(CreateAuthorRequest authorRequest) {

    Author authorResponse = new Author();
    authorResponse.setName(authorRequest.getName());
    authorResponse.setBiography(authorRequest.getBiography());

    return authorRepository.save(authorResponse);
  }
}
