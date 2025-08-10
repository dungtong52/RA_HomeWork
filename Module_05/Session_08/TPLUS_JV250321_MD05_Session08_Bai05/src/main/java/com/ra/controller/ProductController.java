package com.ra.controller;

import com.ra.model.Product;
import com.ra.service.CloudinaryService;
import com.ra.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = {"", "/products"})
public class ProductController {
    private final ProductService productService;
    private final CloudinaryService cloudinaryService;

    public ProductController(ProductService productService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "list";
    }

    @GetMapping("/add")
    public String initAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult result,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add";
        }
        try {
            String imageUrl = cloudinaryService.uploadFile(imageFile);
            product.setImage(imageUrl);
        } catch (IOException e) {
            model.addAttribute("errorMsg", "Upload ảnh thất bại");
            return "add";
        }
        boolean addSuccess = productService.save(product);
        if (!addSuccess) {
            model.addAttribute("errorMsg", "Thêm không thành công");
            return "add";
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String initEditProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            model.addAttribute("errorMsg", "Sản phẩm không tồn tại");
            return "list";
        }
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "update";
        }
        try {
            String imageUrl = cloudinaryService.uploadFile(imageFile);
            product.setImage(imageUrl);
        } catch (IOException e) {
            model.addAttribute("errorMsg", "Upload ảnh thất bại");
            return "update";
        }
        boolean editSuccess = productService.update(product);
        if (!editSuccess) {
            model.addAttribute("errorMsg", "Cập nhật không thành công");
            return "update";
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Cập nhật thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleteSuccess = productService.delete(id);
        if (!deleteSuccess) {
            redirectAttributes.addFlashAttribute("errorMsg", "Xóa không thành công");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa thành công");
        }
        return "redirect:/";
    }
}
