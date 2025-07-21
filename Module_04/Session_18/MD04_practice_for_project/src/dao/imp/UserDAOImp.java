package dao.imp;

import dao.UserDAO;
import entity.Bill;
import entity.PaginationResult;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {
    @Override
    public PaginationResult<Bill> getAllReceiptForUser(String empId, int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Bill> billPaginationResult = null;
        List<Bill> billList;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_receipt_for_user(?,?,?,?)}");
            callableStatement.setString(1, empId);
            callableStatement.setInt(2, size);
            callableStatement.setInt(3, currentPage);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            billList = new ArrayList<>();
            billPaginationResult = new PaginationResult<>();

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getLong("bill_id"));
                bill.setBillCode(resultSet.getString("bill_code"));
                bill.setBillType(resultSet.getBoolean("bill_type"));
                bill.setEmpIdCreated(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("created").toLocalDate());
                bill.setEmpIdAuth(resultSet.getString("emp_id_auth"));
                bill.setAuthDate(resultSet.getDate("auth_date").toLocalDate());
                bill.setBillStatus(resultSet.getShort("bill_status"));
                billList.add(bill);
            }
            billPaginationResult.setTotalPages(callableStatement.getInt(3));
            billPaginationResult.setDataList(billList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return billPaginationResult;
    }

    @Override
    public PaginationResult<Bill> getAllBillForUser(String empId, int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Bill> billPaginationResult = null;
        List<Bill> billList;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_bill_for_user(?,?,?,?)}");
            callableStatement.setString(1, empId);
            callableStatement.setInt(2, size);
            callableStatement.setInt(3, currentPage);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            billList = new ArrayList<>();
            billPaginationResult = new PaginationResult<>();

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getLong("bill_id"));
                bill.setBillCode(resultSet.getString("bill_code"));
                bill.setBillType(resultSet.getBoolean("bill_type"));
                bill.setEmpIdCreated(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("created").toLocalDate());
                bill.setEmpIdAuth(resultSet.getString("emp_id_auth"));
                bill.setAuthDate(resultSet.getDate("auth_date").toLocalDate());
                bill.setBillStatus(resultSet.getShort("bill_status"));
                billList.add(bill);
            }
            billPaginationResult.setTotalPages(callableStatement.getInt(3));
            billPaginationResult.setDataList(billList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return billPaginationResult;
    }
}
