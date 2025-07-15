import business.OrderBusiness;
import business.ProductBusiness;
import business.imp.OrderBusinessImp;
import business.imp.ProductBusinessImp;
import entity.Product;

import java.util.Scanner;

public class Main {
    private static OrderBusiness orderBusiness = new OrderBusinessImp();
    private static ProductBusiness productBusiness = new ProductBusinessImp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*************** MENU ****************");
            System.out.println("1. Tạo đơn hàng mới");
            System.out.println("2. Cập nhật số lượng sản phẩm");
            System.out.println("3. Hiển thị danh sách tất cả sản phẩm");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    orderBusiness.createOrder(scanner);
                    break;
                case 2:
                    productBusiness.updateProductStock(1, 10);
                    break;
                case 3:
                    productBusiness.getAllProducts();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Nhập số nguyên 1-4");
            }
        }


    }
}
