package com.ra.controller;

import com.ra.entity.Product;
import com.ra.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("productName");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String quantity = req.getParameter("quantity");

        Product product = new Product(Integer.parseInt(id),
                name, Float.parseFloat(price), description,
                Integer.parseInt(quantity), Integer.parseInt(quantity) > 0);

        productService.addProduct(product);

        resp.sendRedirect("/AllProductServlet");
    }
}
