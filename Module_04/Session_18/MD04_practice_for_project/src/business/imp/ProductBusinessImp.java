package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import pagination.PaginationBusiness;
import pagination.PaginationResult;
import entity.Product;

import java.util.List;

public class ProductBusinessImp implements ProductBusiness, PaginationBusiness<Product> {
    private final ProductDAO productDAO;

    public ProductBusinessImp() {
        productDAO = new ProductDAOImp();
    }

    @Override
    public PaginationResult<Product> getPaginationData(String key, int size, int currentPage) {
        if (key == null) {
            return productDAO.getProductPagination(size, currentPage);
        }
        return productDAO.getProductByName(key, size, currentPage);
    }

    @Override
    public Product getProductById(String productId) {
        return productDAO.getProductById(productId);
    }

    @Override
    public boolean checkExistProductName(String productName) {
        return productDAO.checkExistProductName(productName);
    }

    @Override
    public boolean createProduct(Product product) {
        return productDAO.createProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean updateProductStatus(String productId, boolean status) {
        return productDAO.updateProductStatus(productId, status);
    }
}
