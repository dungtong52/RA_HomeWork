package business.imp;

import business.OrderBusiness;
import dao.OrderDAO;
import dao.imp.OrderDAOImp;
import entity.Order;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class OrderBusinessImp implements OrderBusiness {
    private final OrderDAO orderDAO = new OrderDAOImp();

    @Override
    public void createOrder(Scanner scanner) {
        Order order = new Order();
        order.inputData(scanner);

        boolean success = orderDAO.createOrder(order);
        if (success) {
            System.out.println("Tạo đơn hàng thành công.");
        } else {
            System.err.println("Tạo đơn hàng thất bại.");
        }
    }

    @Override
    public void listAllOrders() {
        List<Order> orders = orderDAO.listAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    @Override
    public void getOrdersByCustomer(Scanner scanner) {
        System.out.print("Nhập ID khách hàng: ");
        String customerId = scanner.nextLine();
        if(Validation.isPositiveInteger(customerId)) {
            List<Order> orders = orderDAO.getOrdersByCustomer(Integer.parseInt(customerId));
            if (orders.isEmpty()) {
                System.out.println("Không tìm thấy đơn hàng cho khách hàng này.");
            } else {
                orders.forEach(System.out::println);
            }
        } else {
            System.err.println("ID không hợp lệ.");
        }
    }
}
