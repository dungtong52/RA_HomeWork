package com.ra.service;

import com.ra.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Integer id);
}
