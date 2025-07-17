package business;

import entity.Employee;

public interface EmployeeBusiness {
    Employee getEmployeeById(String employeeId);

    boolean checkExistEmployeeName(String employeeName);

    boolean createEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean updateEmployeeStatus(String employeeId, short status);
}
