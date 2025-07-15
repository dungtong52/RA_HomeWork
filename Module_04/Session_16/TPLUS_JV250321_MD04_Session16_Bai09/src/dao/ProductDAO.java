package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {
    boolean checkStock(int productId, int quantity);

    boolean updateProductStock(int productId, int quantity);

    List<Product> getAllProduct();
}
