package dao;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    boolean addEmployee(Employee employee);
    Employee findByName(String name);
    Employee findById(int id);
    boolean updateSalary(int employeeId, double newSalary);
    List<Employee> getAllEmployees();
}
