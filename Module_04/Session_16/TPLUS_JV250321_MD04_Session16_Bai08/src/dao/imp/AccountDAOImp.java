package dao.imp;

import dao.AccountDAO;
import entity.Account;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AccountDAOImp implements AccountDAO {
    @Override
    public Account getAccountInfo(int accountId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Account account = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_account_info(?)}");
            callableStatement.setInt(1, accountId);
            ResultSet resultSet = callableStatement.executeQuery();
            account = new Account();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setBalance(resultSet.getBigDecimal("balance"));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return account;
    }

    public static boolean isExists(int accountId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call isExists(?)}");
            callableStatement.setInt(1, accountId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
