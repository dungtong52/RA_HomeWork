package business;

import pagination.PaginationResult;
import entity.Product;

import java.util.List;

public interface ProductBusiness {
    Product getProductById(String productId);

    boolean checkExistProductName(String productName);

    boolean createProduct(Product product);

    boolean updateProduct(Product product);

    boolean updateProductStatus(String productId, boolean status);


}
