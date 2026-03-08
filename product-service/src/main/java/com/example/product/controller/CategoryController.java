package com.example.product.controller;

import com.example.product.entity.Category;
import com.example.product.model.CreateCategoryRequest;
import com.example.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public Category createCategory(@RequestBody CreateCategoryRequest categoryRequest) {

    return categoryService.createCategory(categoryRequest);
  }
}
