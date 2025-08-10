package com.ra.repository;

import com.ra.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(Integer id);

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Integer id);
}
