package dao;

import entity.Order;

import java.util.List;

public interface OrderDAO {
    boolean createOrder(Order order);
    List<Order> listAllOrders();
    List<Order> getOrdersByCustomer(int customerId);
}
