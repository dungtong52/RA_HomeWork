package dao;

import entity.Product;

public interface ProductDAO {
    boolean addProduct(Product product);
    Product findByName(String name);
}
