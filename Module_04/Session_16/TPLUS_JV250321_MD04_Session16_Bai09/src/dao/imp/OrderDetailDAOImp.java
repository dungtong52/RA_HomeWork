package dao.imp;

import dao.OrderDetailDAO;
import entity.OrderDetail;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class OrderDetailDAOImp implements OrderDetailDAO {
    @Override
    public boolean createOrderDetail(List<OrderDetail> orderDetailList) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            for (OrderDetail orderDetail : orderDetailList) {
                callableStatement = connection.prepareCall("{call create_order_detail(?,?,?)}");
                callableStatement.setInt(1, orderDetail.getOrderId());
                callableStatement.setInt(2, orderDetail.getProductId());
                callableStatement.setInt(3, orderDetail.getQuantity());
                callableStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
