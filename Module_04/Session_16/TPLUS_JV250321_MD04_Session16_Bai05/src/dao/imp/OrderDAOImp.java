package dao.imp;

import dao.OrderDAO;
import utils.ConnectionDB;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class OrderDAOImp implements OrderDAO {
    @Override
    public int placeOrder(int customerId, int productId, int quantity, BigDecimal unitPrice) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int orderId = 0;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call place_order(?,?,?,?,?)}");
            callableStatement.setInt(1, customerId);
            callableStatement.setInt(2, productId);
            callableStatement.setInt(3, quantity);
            callableStatement.setBigDecimal(4, unitPrice);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            orderId = callableStatement.getInt(5);
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.out.println("Lỗi khi lập order: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi khi rollback " + ex.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return orderId;
    }
}
