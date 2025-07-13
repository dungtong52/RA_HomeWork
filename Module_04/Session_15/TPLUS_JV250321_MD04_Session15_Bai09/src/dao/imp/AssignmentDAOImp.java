package dao.imp;

import dao.AssignmentDAO;
import entity.Assignment;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAOImp implements AssignmentDAO {
    @Override
    public boolean assignEmployeeToProject(Assignment assignment) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_assign_employee_to_project(?, ?, ?)}");
            callSt.setInt(1, assignment.getEmployeeId());
            callSt.setInt(2, assignment.getProjectId());
            callSt.setString(3, assignment.getRole());
            return callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi gán nhân viên vào dự án: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<Assignment> getAllAssignments() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Assignment> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_get_all_assignments()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setEmployeeId(rs.getInt("employee_id"));
                a.setProjectId(rs.getInt("project_id"));
                a.setRole(rs.getString("role"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách assignment: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}
