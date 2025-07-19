package dao.imp;

import dao.BillReceiptDetailDAO;
import entity.BillDetail;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReceiptDetailsDAOImp implements BillReceiptDetailDAO {
    @Override
    public boolean createBatchDetails(List<BillDetail> billDetailList) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            for (BillDetail billDetail : billDetailList) {
                callableStatement = connection.prepareCall("{call create_receipt_detail(?,?,?,?)}");
                callableStatement.setLong(1, billDetail.getBillId());
                callableStatement.setString(2, billDetail.getProductId());
                callableStatement.setInt(3, billDetail.getQuantity());
                callableStatement.setFloat(4, billDetail.getPrice());
                callableStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
