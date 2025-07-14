package dao;

import java.math.BigDecimal;

public interface OrderDAO {
    int placeOrder(int customerId, int productId, int quantity, BigDecimal unitPrice);
}
