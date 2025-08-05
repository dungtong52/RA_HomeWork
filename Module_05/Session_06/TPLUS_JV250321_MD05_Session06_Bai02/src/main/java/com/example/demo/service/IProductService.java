package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(int id);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);
}
