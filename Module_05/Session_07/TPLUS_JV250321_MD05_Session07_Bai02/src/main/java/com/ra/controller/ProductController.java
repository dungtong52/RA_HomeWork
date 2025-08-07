package com.ra.controller;

import com.ra.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @GetMapping("/")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        model.addAttribute("product", product);
        return "result";
    }
}

