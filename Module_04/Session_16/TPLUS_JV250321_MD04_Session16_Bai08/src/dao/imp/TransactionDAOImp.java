package dao.imp;

import dao.TransactionDAO;
import entity.Transaction;
import utils.ConnectionDB;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImp implements TransactionDAO {
    @Override
    public boolean transferMoney(int fromAccountId, int toAccountId, BigDecimal amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call transfer_money(?,?,?)}");
            callableStatement.setInt(1, fromAccountId);
            callableStatement.setInt(2, toAccountId);
            callableStatement.setBigDecimal(3, amount);
            callableStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Transaction> listTransactions(int accountId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Transaction> transactionList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call list_transactions(?)}");
            callableStatement.setInt(1, accountId);
            ResultSet resultSet = callableStatement.executeQuery();
            transactionList = new ArrayList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setFromAccountId(resultSet.getInt("from_account_id"));
                transaction.setToAccountId(resultSet.getInt("to_account_id"));
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setTransactionDate(resultSet.getTimestamp("transaction_date").toLocalDateTime());
                transactionList.add(transaction);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return transactionList;
    }
}
