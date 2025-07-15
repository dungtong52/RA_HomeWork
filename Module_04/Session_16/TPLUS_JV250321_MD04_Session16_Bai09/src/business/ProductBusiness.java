package business;

public interface ProductBusiness {
    boolean updateProductStock(int productId, int quantity);
    void getAllProducts();
}
