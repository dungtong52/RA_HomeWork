package business.imp;

import business.OrderBusiness;
import dao.OrderDAO;
import dao.imp.OrderDAOImp;

import java.math.BigDecimal;

public class OrderBusinessImp implements OrderBusiness {
private static final OrderDAO orderDAO = new OrderDAOImp();
    @Override
    public void createOrder(int customerId, int productId, int quantity, BigDecimal unitPrice) {
        int orderId = orderDAO.placeOrder(customerId,productId,quantity, unitPrice);
        if(orderId > 0){
            System.out.println("Thêm mới đơn hàng thành công!");
        } else {
            System.err.println("Thêm mới thất bại! ");
        }
    }
}
