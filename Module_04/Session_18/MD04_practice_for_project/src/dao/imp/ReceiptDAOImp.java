package dao.imp;

import dao.AbstractBillReceiptDAO;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ReceiptDAOImp extends AbstractBillReceiptDAO {

    @Override
    public boolean acceptBill(long billId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call accept_receipt(?)}");
            callableStatement.setLong(1, billId);
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
