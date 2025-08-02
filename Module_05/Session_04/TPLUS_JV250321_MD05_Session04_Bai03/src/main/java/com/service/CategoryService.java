package com.service;

import com.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(int id);

    boolean createCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);
}
