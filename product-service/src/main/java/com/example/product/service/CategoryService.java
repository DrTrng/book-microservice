package com.example.product.service;

import com.example.product.entity.Category;
import com.example.product.model.CreateCategoryRequest;

public interface CategoryService {

  Category createCategory(CreateCategoryRequest categoryRequest);
}
