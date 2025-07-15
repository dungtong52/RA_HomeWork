package dao;

import entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
    boolean createOrderDetail(List<OrderDetail> orderDetailList);
}
