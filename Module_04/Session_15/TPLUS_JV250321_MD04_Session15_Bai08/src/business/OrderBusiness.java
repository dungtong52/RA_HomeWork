package business;

import java.util.Scanner;

public interface OrderBusiness {
    void createOrder(Scanner scanner);
    void listAllOrders();
    void getOrdersByCustomer(Scanner scanner);
}
