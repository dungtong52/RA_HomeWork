package business.imp;

import business.EmployeeBusiness;
import dao.EmployeeDAO;
import dao.imp.EmployeeDAOImp;
import entity.Employee;
import business.PaginationBusiness;
import entity.PaginationResult;

public class EmployeeBusinessImp implements EmployeeBusiness, PaginationBusiness<Employee> {
    private final EmployeeDAO employeeDAO;

    public EmployeeBusinessImp() {
        employeeDAO = new EmployeeDAOImp();
    }

    @Override
    public PaginationResult<Employee> getPaginationData(String key, int size, int currentPage) {
        if (key == null) {
            return employeeDAO.getEmployeePagination(size, currentPage);
        }
        return employeeDAO.getEmployeeByName(key, size, currentPage);
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    @Override
    public boolean checkExistEmployeeName(String employeeName) {
        return employeeDAO.checkExistEmployeeName(employeeName);
    }

    @Override
    public boolean createEmployee(Employee employee) {
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean updateEmployeeStatus(String employeeId, short status) {
        return employeeDAO.updateEmployeeStatus(employeeId, status);
    }
}
