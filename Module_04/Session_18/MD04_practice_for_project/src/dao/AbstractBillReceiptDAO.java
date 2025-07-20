package dao;

import entity.Bill;
import entity.PaginationResult;
import utils.ConnectionDB;

import java.sql.*;

public abstract class AbstractBillReceiptDAO implements BillReceiptDAO {
    public abstract PaginationResult<Bill> getBillPagination(int size, int currentPage);

    @Override
    public boolean checkExistBillCode(String billCode) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_bill_code(?)}");
            callableStatement.setString(1, billCode);
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
    public long createBill(Bill bill) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_bill(?,?,?,?)}");
            callableStatement.setString(1, bill.getBillCode());
            callableStatement.setBoolean(2, bill.isBillType());
            callableStatement.setString(3, bill.getEmpIdCreated());
            callableStatement.registerOutParameter(4, Types.BIGINT);
            callableStatement.executeUpdate();
            return callableStatement.getLong(4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return 0;
    }

    @Override
    public Bill findBillByCode(String billCode) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Bill bill = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call find_bill_by_code(?)}");
            callableStatement.setString(1, billCode);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                bill = new Bill();
                bill.setBillId(resultSet.getLong("bill_id"));
                bill.setBillCode(resultSet.getString("bill_code"));
                bill.setBillType(resultSet.getBoolean("bill_type"));
                bill.setEmpIdCreated(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("created").toLocalDate());
                bill.setEmpIdAuth(resultSet.getString("emp_id_auth"));
                bill.setAuthDate(resultSet.getDate("auth_date").toLocalDate());
                bill.setBillStatus(resultSet.getShort("bill_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return bill;
    }

    @Override
    public boolean updateBill(Bill bill) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_bill(?,?,?,?,?,?)}");
            callableStatement.setString(1, bill.getBillCode());
            callableStatement.setString(2, bill.getEmpIdCreated());
            callableStatement.setDate(3, Date.valueOf(bill.getCreated()));
            callableStatement.setString(4, bill.getEmpIdAuth());
            callableStatement.setDate(5, Date.valueOf(bill.getAuthDate()));
            callableStatement.setShort(6, bill.getBillStatus());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
