package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.IProductService;

import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProduct(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "product-list";
    }

    @GetMapping("/edit")
    public String showAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
