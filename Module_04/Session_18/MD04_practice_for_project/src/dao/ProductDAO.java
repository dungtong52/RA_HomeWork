package dao;

import pagination.PaginationResult;
import entity.Product;

import java.util.List;

public interface ProductDAO {
    PaginationResult<Product> getProductPagination(int size, int currentPage);

    Product getProductById(String productId);

    boolean checkExistProductName(String productName);

    boolean createProduct(Product product);

    PaginationResult<Product> getProductByName(String productName, int size, int currentPage);

    boolean updateProduct(Product product);

    boolean updateProductStatus(String productId, boolean status);

}
