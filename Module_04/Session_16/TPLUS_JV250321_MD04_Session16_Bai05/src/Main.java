import business.OrderBusiness;
import business.imp.OrderBusinessImp;

import java.math.BigDecimal;

public class Main {
    private static final OrderBusiness orderBusiness = new OrderBusinessImp();

    public static void main(String[] args) {
        orderBusiness.createOrder(1, 2, 2, new BigDecimal(20000000));
    }
}
