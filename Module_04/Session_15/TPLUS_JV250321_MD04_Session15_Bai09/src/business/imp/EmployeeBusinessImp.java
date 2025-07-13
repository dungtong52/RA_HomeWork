package business.imp;

import business.EmployeeBusiness;
import dao.EmployeeDAO;
import dao.imp.EmployeeDAOImp;
import entity.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeBusinessImp implements EmployeeBusiness {
    private final EmployeeDAO employeeDAO = new EmployeeDAOImp();

    @Override
    public void createEmployee(Scanner scanner) {
        Employee employee = new Employee();
        employee.inputData(scanner);

        // Kiểm tra nhân viên đã tồn tại (theo tên)
        Employee existing = employeeDAO.findByName(employee.getName());
        if (existing != null) {
            System.err.println("Nhân viên đã tồn tại.");
            return;
        }

        boolean success = employeeDAO.addEmployee(employee);
        if (success) {
            System.out.println("Thêm nhân viên thành công.");
        } else {
            System.err.println("Thêm nhân viên thất bại.");
        }
    }

    @Override
    public void updateEmployeeSalary(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần cập nhật lương: ");
        String idStr = scanner.nextLine();
        if (!isInteger(idStr)) {
            System.err.println("ID không hợp lệ.");
            return;
        }
        int id = Integer.parseInt(idStr);
        Employee emp = employeeDAO.findById(id);
        if (emp == null) {
            System.err.println("Không tìm thấy nhân viên.");
            return;
        }

        System.out.print("Nhập lương mới: ");
        String salaryStr = scanner.nextLine();
        try {
            double newSalary = Double.parseDouble(salaryStr);
            if (employeeDAO.updateSalary(id, newSalary)) {
                System.out.println("Cập nhật lương thành công.");
            } else {
                System.err.println("Cập nhật lương thất bại.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Lương không hợp lệ.");
        }
    }

    @Override
    public void showAllEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("Không có nhân viên nào.");
            return;
        }
        list.forEach(System.out::println);
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
