package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import entity.Product;

import java.util.Scanner;

public class ProductBusinessImp implements ProductBusiness {
    private final ProductDAO productDAO = new ProductDAOImp();

    @Override
    public void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);

        Product existing = productDAO.findByName(product.getName());
        if (existing == null) {
            boolean success = productDAO.addProduct(product);
            if (success) {
                System.out.println("Thêm sản phẩm thành công.");
            } else {
                System.err.println("Thêm sản phẩm thất bại.");
            }
        } else {
            System.err.println("Sản phẩm đã tồn tại với tên: " + product.getName());
        }
    }
}
