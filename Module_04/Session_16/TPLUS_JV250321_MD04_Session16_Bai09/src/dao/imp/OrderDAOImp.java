package dao.imp;

import dao.OrderDAO;
import entity.OrderDetail;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

public class OrderDAOImp implements OrderDAO {
    @Override
    public int createOrder(String customerName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int orderId = 0;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_order(?,CURRENT_DATE, ?)}");
            callableStatement.setString(1, customerName);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            orderId = callableStatement.getInt(2);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return orderId;
    }
}
