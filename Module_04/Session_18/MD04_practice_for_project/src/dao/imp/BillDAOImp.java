package dao.imp;

import dao.BillReceiptDAO;
import entity.Bill;
import entity.PaginationResult;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImp implements BillReceiptDAO {
    @Override
    public PaginationResult<Bill> getBillPagination(int size, int currentPage) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        PaginationResult<Bill> billPaginationResult = null;
        List<Bill> billList;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_bill_pagination(?,?,?)}");
            callableStatement.setInt(1, size);
            callableStatement.setInt(2, currentPage);
            callableStatement.registerOutParameter(3, Types.INTEGER);
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
    public long createBill(Bill bill) {
        return 0;
    }

    @Override
    public boolean updateBill(Bill bill) {
        return false;
    }
}
