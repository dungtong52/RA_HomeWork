package dao.imp;

import dao.AccountDAO;
import entity.Account;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AccountDAOImp implements AccountDAO {
    @Override
    public Account getAccountByUserName(String userName, String password) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Account account = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_account_by_user_name(?,?)}");
            callableStatement.setString(1, userName);
            callableStatement.setString(2, password);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setAccId(resultSet.getInt("acc_id"));
                account.setPermission(resultSet.getBoolean("permission"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return account;
    }
}
