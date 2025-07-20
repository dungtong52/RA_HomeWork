package dao;

import entity.Employee;
import entity.PaginationResult;

public interface EmployeeDAO {
    PaginationResult<Employee> getEmployeePagination(int size, int currentPage);

    boolean createEmployee(Employee employee);

    Employee getEmployeeById(String employeeId);

    boolean checkExistEmployeeName(String employeeName);

    boolean updateEmployee(Employee employee);

    PaginationResult<Employee> getEmployeeByName(String employeeName, int size, int currentPage);

    boolean updateEmployeeStatus(String employeeId, short status);
}
