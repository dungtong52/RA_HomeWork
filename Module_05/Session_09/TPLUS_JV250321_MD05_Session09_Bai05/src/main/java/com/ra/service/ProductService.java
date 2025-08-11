package com.ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.model.CartItems;
import com.ra.model.Product;
import com.ra.repo.ProductRepo;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProductList() {
        return productRepo.getProductList();
    }

    // Cart Items
    public boolean addCartItems(CartItems cartItems) {
        return productRepo.addCartItems(cartItems);
    }

    public Product getProduct(String name) {
        return productRepo.getProduct(name);
    }

    public List<CartItems> getCartItemsList() {
        return productRepo.getCartItemsList();
    }

}
