package com.ra.service;

import com.ra.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> productList = new ArrayList<>();

    public List<Product> getAllProducts() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }
}
