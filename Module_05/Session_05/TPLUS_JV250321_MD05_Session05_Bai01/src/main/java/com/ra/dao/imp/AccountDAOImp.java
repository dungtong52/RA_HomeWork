package com.ra.dao.imp;

import com.ra.dao.AccountDAO;
import com.ra.model.Account;
import com.ra.model.enumClass.RoleEnum;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AccountDAOImp implements AccountDAO {

    @Override
    public Account getAccountLogin(String username, String password) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Account account = null;
        try{
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_account_by_user(?,?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()){
                account = new Account();
                account.setAccountId(resultSet.getLong("id"));
                account.setUsername(resultSet.getString("username"));
                account.setRole(RoleEnum.valueOf(resultSet.getString("role")));
                account.setStatus(resultSet.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return account;
    }
}
