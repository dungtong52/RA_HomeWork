package dao.imp;

import dao.EmployeeDAO;
import entity.Employee;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {
    @Override
    public boolean addEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_add_employee(?, ?, ?)}");
            callSt.setString(1, employee.getName());
            callSt.setString(2, employee.getDepartment());
            callSt.setDouble(3, employee.getSalary());
            int rows = callSt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi thêm nhân viên: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public Employee findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_employee_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setSalary(rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm nhân viên theo tên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }

    @Override
    public Employee findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_find_employee_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setSalary(rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm nhân viên theo ID: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }

    @Override
    public boolean updateSalary(int employeeId, double newSalary) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_update_employee_salary(?, ?)}");
            callSt.setInt(1, employeeId);
            callSt.setDouble(2, newSalary);
            int rows = callSt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật lương: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> employees = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sp_get_all_employees()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách nhân viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
}
