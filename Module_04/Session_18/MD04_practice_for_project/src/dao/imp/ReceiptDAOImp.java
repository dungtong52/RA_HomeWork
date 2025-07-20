package dao.imp;

import dao.AbstractBillReceiptDAO;
import entity.Bill;
import entity.PaginationResult;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImp extends AbstractBillReceiptDAO {
    @Override
    public PaginationResult<Bill> getBillPagination(int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Bill> receiptPaginationResult = null;
        List<Bill> receiptList;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_receipt_pagination(?,?,?)}");
            callableStatement.setInt(1, size);
            callableStatement.setInt(2, currentPage);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            receiptList = new ArrayList<>();
            receiptPaginationResult = new PaginationResult<>();
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
                receiptList.add(bill);
            }
            receiptPaginationResult.setTotalPages(callableStatement.getInt(3));
            receiptPaginationResult.setDataList(receiptList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return receiptPaginationResult;
    }
}
