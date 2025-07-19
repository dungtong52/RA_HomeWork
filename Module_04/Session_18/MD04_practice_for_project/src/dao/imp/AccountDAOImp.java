package dao.imp;

import dao.AccountDAO;
import entity.Account;
import entity.PaginationResult;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImp implements AccountDAO {
    @Override
    public Account getAccountToLogin(String userName, String password) {
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

    @Override
    public PaginationResult<Account> getAllAccountPagination(int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Account> paginationResult = null;
        List<Account> accountList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_account_pagination(?,?,?)}");
            callableStatement.setInt(1, size);
            callableStatement.setInt(2, currentPage);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            paginationResult = new PaginationResult<>();
            accountList = new ArrayList<>();

            while (resultSet.next()) {
                Account account = new Account();
                account.setAccId(resultSet.getInt("acc_id"));
                account.setUserName(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("password"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmpId(resultSet.getString("emp_id"));
                account.setAccStatus(resultSet.getBoolean("acc_status"));
                accountList.add(account);
            }
            paginationResult.setTotalPages(callableStatement.getInt(3));
            paginationResult.setDataList(accountList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return paginationResult;
    }

    @Override
    public boolean createAccount(Account account) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_account(?,?,?)}");
            callableStatement.setString(1, account.getUserName());
            callableStatement.setString(2, account.getPassword());
            callableStatement.setString(3, account.getEmpId());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public Account getAccountById(int accountId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Account account = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_account_by_id(?)}");
            callableStatement.setInt(1, accountId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setAccId(resultSet.getInt("acc_id"));
                account.setUserName(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("password"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmpId(resultSet.getString("emp_id"));
                account.setAccStatus(resultSet.getBoolean("acc_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return account;
    }

    @Override
    public boolean checkExistAccountName(String accountName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_account_name(?)}");
            callableStatement.setString(1, accountName);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean checkExistEmpId(String empId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_emp_id(?)}");
            callableStatement.setString(1, empId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateAccountStatus(Account account) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_account_status(?,?)}");
            callableStatement.setInt(1, account.getAccId());
            callableStatement.setBoolean(2, account.isAccStatus());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public PaginationResult<Account> getAccountByUserName(String accountName, int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Account> accountPaginationResult = null;
        List<Account> accountList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_account_by_name(?,?,?,?)}");
            callableStatement.setString(1, accountName);
            callableStatement.setInt(2, size);
            callableStatement.setInt(3, currentPage);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            accountList = new ArrayList<>();
            accountPaginationResult = new PaginationResult<>();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccId(resultSet.getInt("acc_id"));
                account.setUserName(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("password"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmpId(resultSet.getString("emp_id"));
                account.setAccStatus(resultSet.getBoolean("acc_status"));
                accountList.add(account);
            }
            accountPaginationResult.setTotalPages(callableStatement.getInt(4));
            accountPaginationResult.setDataList(accountList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return accountPaginationResult;
    }
}
