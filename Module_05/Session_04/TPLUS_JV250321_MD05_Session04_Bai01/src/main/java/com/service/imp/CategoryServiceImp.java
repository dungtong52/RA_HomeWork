package com.service.imp;

import com.model.dao.CategoryDAO;
import com.model.dao.imp.CategoryImp;
import com.model.entity.Category;
import com.service.CategoryService;

import java.util.List;

public class CategoryServiceImp implements CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryServiceImp() {
        categoryDAO = new CategoryImp();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public boolean createCategory(Category category) {
        return categoryDAO.createCategory(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryDAO.deleteCategory(id);
    }
}
