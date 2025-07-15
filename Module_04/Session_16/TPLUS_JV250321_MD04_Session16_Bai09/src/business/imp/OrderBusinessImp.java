package business.imp;

import business.OrderBusiness;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.imp.OrderDAOImp;
import dao.imp.OrderDetailDAOImp;
import dao.imp.ProductDAOImp;
import entity.Order;
import entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderBusinessImp implements OrderBusiness {
    private OrderDAO orderDAO = new OrderDAOImp();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImp();
    private ProductDAO productDAO = new ProductDAOImp();

    @Override
    public void createOrder(Scanner scanner) {
        System.out.println("Nhập tên khách hàng");
        String orderName = scanner.nextLine();
        int orderId = orderDAO.createOrder(orderName);
        if (orderId != 0) {
            List<OrderDetail> orderDetailList = new ArrayList<>();

            System.out.println("Nhập vào mã Id của sản phẩm muốn mua:");
            int productId = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập số lượng muốn mua:");
            int quantity = Integer.parseInt(scanner.nextLine());
            OrderDetail orderDetail = new OrderDetail();
            if (productDAO.checkStock(productId, quantity)) {
                orderDetail.setOrderId(orderId);
                orderDetail.setProductId(productId);
                orderDetail.setQuantity(quantity);
                orderDetailList.add(orderDetail);
            }
            boolean success = orderDetailDAO.createOrderDetail(orderDetailList);
            if (success) {
                productDAO.updateProductStock(productId, quantity);
                System.out.println("Tạo đơn hàng thành công");
            } else {
                System.err.println("Tạo đơn hàng thất bại!");
            }
        } else {
            System.out.println("Không tạo được đơn hàng!");
        }
    }
}
