import business.CustomerBusiness;
import business.OrderBusiness;
import business.ProductBusiness;
import business.imp.CustomerBusinessImp;
import business.imp.OrderBusinessImp;
import business.imp.ProductBusinessImp;
import validation.Validation;

import java.util.Scanner;

public class Main {
    private final ProductBusiness productBusiness;
    private final CustomerBusiness customerBusiness;
    private final OrderBusiness orderBusiness;

    public Main() {
        productBusiness = new ProductBusinessImp();
        customerBusiness = new CustomerBusinessImp();
        orderBusiness = new OrderBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        do {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Hiển thị tất cả đơn hàng");
            System.out.println("5. Tìm đơn hàng theo khách hàng");
            System.out.println("6. Thoát");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.productBusiness.createProduct(scanner);
                    case 2 -> main.customerBusiness.updateCustomer(scanner);
                    case 3 -> main.orderBusiness.createOrder(scanner);
                    case 4 -> main.orderBusiness.listAllOrders();
                    case 5 -> main.orderBusiness.getOrdersByCustomer(scanner);
                    case 6 -> System.exit(0);
                }
            } else {
                System.err.println("Vui lòng chọn từ 1 đến 6.");
            }
        } while (true);
    }
}
