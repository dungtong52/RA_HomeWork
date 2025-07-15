package dao;

import entity.Order;
import entity.OrderDetail;

import java.util.List;

public interface OrderDAO {
    int createOrder(String customerName);
}
