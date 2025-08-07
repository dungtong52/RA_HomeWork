package com.ra.repo;

import com.ra.model.Category;

import java.util.List;

public interface CategoryRepo {
    List<Category> getAllCategory();

    boolean checkExistCategoryName(String name);

    boolean saveCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);

    Category getCategoryById(int id);
}
