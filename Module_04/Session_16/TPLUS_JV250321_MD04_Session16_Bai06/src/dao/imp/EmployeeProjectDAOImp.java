package dao.imp;

import dao.EmployeeProjectDAO;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeProjectDAOImp implements EmployeeProjectDAO {
    @Override
    public boolean assignEmployeeToProject(int employeeId, int projectId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call assign_employee_to_project(?,?)}");
            callableStatement.setInt(1, employeeId);
            callableStatement.setInt(2, projectId);
            callableStatement.execute();
            connection.commit();
            return true;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("Lỗi rollback: " + ex.getMessage());
                }
            }
            System.err.println("Không thể gán nhân viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
