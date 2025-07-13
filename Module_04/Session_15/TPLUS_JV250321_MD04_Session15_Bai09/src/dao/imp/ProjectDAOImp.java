package dao.imp;

import dao.ProjectDAO;
import entity.Project;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImp implements ProjectDAO {
    @Override
    public boolean addProject(Project project) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_add_project(?, ?)}");
            callSt.setString(1, project.getName());
            callSt.setDouble(2, project.getBudget());
            return callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm dự án: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public Project findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Project project = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_project_by_name(?)}");
            callSt.setString(1, name);
            rs = callSt.executeQuery();
            if (rs.next()) {
                project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setBudget(rs.getDouble("budget"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm dự án theo tên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return project;
    }

    @Override
    public Project findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Project project = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_project_by_id(?)}");
            callSt.setInt(1, id);
            rs = callSt.executeQuery();
            if (rs.next()) {
                project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setBudget(rs.getDouble("budget"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm dự án theo ID: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return project;
    }

    @Override
    public List<Project> getAllProjects() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Project> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_get_all_projects()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setBudget(rs.getDouble("budget"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách dự án: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}
