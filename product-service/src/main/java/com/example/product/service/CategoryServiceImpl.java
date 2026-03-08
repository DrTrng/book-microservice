package com.example.product.service;

import com.example.product.entity.Category;
import com.example.product.model.CreateCategoryRequest;
import com.example.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category createCategory(CreateCategoryRequest request) {

    Category category = new Category();
    category.setName(request.getName());
    category.setDescription(request.getDescription());

    if (request.getParentId() != null) {
      Category parent =
          categoryRepository
              .findById(request.getParentId())
              .orElseThrow(() -> new RuntimeException("Parent category not found"));

      category.setParent(parent);
    }

    return categoryRepository.save(category);
  }
}
