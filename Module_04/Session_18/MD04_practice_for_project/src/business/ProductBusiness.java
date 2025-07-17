package business;

import pagination.PaginationProduct;
import entity.Product;

import java.util.List;

public interface ProductBusiness {
    PaginationProduct getProductPagination(int size, int currentPage);

    Product getProductById(String productId);

    boolean checkExistProductName(String productName);

    boolean createProduct(Product product);

    List<Product> getProductByName(String productName);

    boolean updateProduct(Product product);

    boolean updateProductStatus(String productId, boolean status);


}
