package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDAOImp;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBusinessImp implements ProductBusiness {
    private ProductDAO productDAO = new ProductDAOImp();

    @Override
    public boolean updateProductStock(int productId, int quantity) {
        if (productDAO.checkStock(productId, quantity)) {
            if (productDAO.updateProductStock(productId, quantity)) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Số lượng sản phẩm trong kho không đủ");
        }
        return false;
    }

    @Override
    public void getAllProducts() {
        List<Product> productList = productDAO.getAllProduct();
        if (!productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm:");
            productList.forEach(System.out::println);
        } else {
            System.out.println("Danh sách chưa có sản phẩm!");
        }
    }
}
