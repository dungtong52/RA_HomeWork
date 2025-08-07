package com.ra.service;

import com.ra.model.Category;
import com.ra.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.getAllCategory();
    }

    @Override
    public boolean checkExistCategoryName(String name) {
        return categoryRepo.checkExistCategoryName(name);
    }

    @Override
    public boolean saveCategory(Category category) {
        return categoryRepo.saveCategory(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryRepo.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryRepo.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.getCategoryById(id);
    }
}
