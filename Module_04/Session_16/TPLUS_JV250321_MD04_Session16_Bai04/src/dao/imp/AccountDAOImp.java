package dao.imp;

import dao.AccountDAO;
import utils.ConnectionDB;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountDAOImp implements AccountDAO {
    @Override
    public boolean transferFunds(int from_account, int to_account, BigDecimal amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call transfer_funds(?,?,?)}");
            callableStatement.setInt(1, from_account);
            callableStatement.setInt(2, to_account);
            callableStatement.setBigDecimal(3, amount);
            callableStatement.execute();
            connection.commit();
            return true;
        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
                System.err.println("Lỗi chuyển khoản " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi rollback " + e.getMessage());
            }
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
