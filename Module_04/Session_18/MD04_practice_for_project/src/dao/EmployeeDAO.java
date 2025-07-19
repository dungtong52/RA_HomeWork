package dao;

import entity.Employee;
import entity.PaginationResult;

public interface EmployeeDAO {
    PaginationResult<Employee> getEmployeePagination(int size, int currentPage);

    Employee getEmployeeById(String employeeId);

    boolean checkExistEmployeeName(String employeeName);

    boolean createEmployee(Employee employee);

    PaginationResult<Employee> getEmployeeByName(String employeeName, int size, int currentPage);

    boolean updateEmployee(Employee employee);

    boolean updateEmployeeStatus(String employeeId, short status);
}
