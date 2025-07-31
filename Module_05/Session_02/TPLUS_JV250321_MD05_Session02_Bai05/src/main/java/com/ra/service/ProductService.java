package com.ra.service;

import com.ra.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList.add(new Product(1, "Iphone 15", 3000, "sản phẩm mới"));
        productList.add(new Product(2, "Iphone 16", 3000, "sản phẩm mới"));
        productList.add(new Product(3, "Iphone 17", 3000, "sản phẩm mới"));
        productList.add(new Product(4, "Iphone 18", 3000, "sản phẩm mới"));
        productList.add(new Product(5, "Iphone 19", 3000, "sản phẩm mới"));
    }

    public List<Product> getAllProduct(){
        return productList;
    }
}
