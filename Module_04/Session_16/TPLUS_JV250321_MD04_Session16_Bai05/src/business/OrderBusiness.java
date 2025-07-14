package business;

import java.math.BigDecimal;

public interface OrderBusiness {
void createOrder(int customerId, int productId, int quantity, BigDecimal unitPrice);
}
