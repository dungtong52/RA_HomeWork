package com.ra.controller;

import com.ra.model.Category;
import com.ra.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAll(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
        return "category-list";
    }

    @GetMapping("/add")
    public String initAddCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category-add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "category-add";
        } else if (categoryService.checkExistCategoryName(category.getCategoryName())) {
            result.rejectValue("categoryName", null,  "Tên danh mục phải là duy nhất");
            return "category-add";
        }
        boolean success = categoryService.saveCategory(category);
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMsg", "Thêm danh mục thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm danh mục thành công");
        }
        return "redirect:/"; //trả về category-list
    }

    @GetMapping("/edit/{id}")
    public String initEditCategory(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Không tìm thấy danh mục để chỉnh sửa");
            return "redirect:/"; //trả về category-list
        }
        model.addAttribute("category", category);
        return "category-edit";
    }

    @PostMapping("/edit")
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "category-edit";
        } else if (categoryService.checkExistCategoryName(category.getCategoryName())) {
            result.rejectValue("categoryName", "Tên danh mục phải là duy nhất");
            return "category-edit";
        }
        boolean success = categoryService.updateCategory(category);
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMsg", "Sửa danh mục thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa danh mục thành công");
        }
        return "redirect:/"; //trả về category-list
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean success = categoryService.deleteCategory(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMsg", "Xóa danh mục thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa danh mục thành công");
        }
        return "redirect:/"; //trả về category-list
    }
}
