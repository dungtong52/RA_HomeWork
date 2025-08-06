package com.ra.repository;

import com.ra.model.Employee;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Employee> employeeList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_employee()}");
            ResultSet resultSet = callableStatement.executeQuery();
            employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPosition(resultSet.getString("position"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Employee employee = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_employee_by_id(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPosition(resultSet.getString("position"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return employee;
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call save_employee(?,?,?)}");
            callableStatement.setString(1, employee.getName());
            callableStatement.setString(2, employee.getEmail());
            callableStatement.setString(3, employee.getPosition());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_employee(?,?,?,?)}");
            callableStatement.setLong(1, employee.getId());
            callableStatement.setString(2, employee.getName());
            callableStatement.setString(3, employee.getEmail());
            callableStatement.setString(4, employee.getPosition());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_employee(?)}");
            callableStatement.setLong(1, id);
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
