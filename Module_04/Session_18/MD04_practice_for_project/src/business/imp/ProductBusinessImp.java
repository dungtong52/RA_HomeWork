package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import pagination.PaginationProduct;
import entity.Product;

import java.util.List;

public class ProductBusinessImp implements ProductBusiness {
    private final ProductDAO productDAO;

    public ProductBusinessImp() {
        productDAO = new ProductDAOImp();
    }

    @Override
    public PaginationProduct getProductPagination(int size, int currentPage) {
        return productDAO.getProductPagination(size, currentPage);
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
    public List<Product> getProductByName(String productName) {
        return productDAO.getProductByName(productName);
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
