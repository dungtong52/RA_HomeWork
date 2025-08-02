package com.controller.sevlet;

import com.model.entity.Category;
import com.service.CategoryService;
import com.service.imp.CategoryServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "findAll" -> findAllCategories(req, resp);
            case "edit" -> {
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                Category category = categoryService.getCategoryById(categoryId);
                req.setAttribute("category", category);
                req.getRequestDispatcher("view/updateCategory.jsp").forward(req, resp);
            }
            case "delete" -> {
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                boolean success = categoryService.deleteCategory(categoryId);
                if (success) {
                    findAllCategories(req, resp);
                } else {
                    req.setAttribute("error", "Có lỗi trong quá trình xóa");
                    req.getRequestDispatcher("view/error.jsp").forward(req, resp);
                }
            }
        }
    }

    public void findAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("view/listCategories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("save")) {
            Category category = new Category();
            category.setCateName(req.getParameter("name"));
            category.setDescription(req.getParameter("description"));
            category.setStatus(Boolean.parseBoolean(req.getParameter("status")));

            boolean success = categoryService.createCategory(category);
            if (success) {
                findAllCategories(req, resp);
            } else {
                req.setAttribute("error", "Có lỗi trong quá trình thêm mới");
                req.getRequestDispatcher("view/error.jsp").forward(req, resp);
            }
        } else if (action.equals("update")) {
            Category category = new Category();
            category.setId(Integer.parseInt(req.getParameter("categoryId")));
            category.setCateName(req.getParameter("name"));
            category.setDescription(req.getParameter("description"));
            category.setStatus(Boolean.parseBoolean(req.getParameter("status")));

            boolean success = categoryService.updateCategory(category);
            if (success) {
                findAllCategories(req, resp);
            } else {
                req.setAttribute("error", "Có lỗi trong quá trình cập nhật");
                req.getRequestDispatcher("view/error.jsp").forward(req, resp);
            }
        }
    }
}
