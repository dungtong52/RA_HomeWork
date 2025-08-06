package com.ra.repository;

import com.ra.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployee();

    Employee getEmployeeById(long id);

    boolean saveEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(long id);
}
