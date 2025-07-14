package business.imp;

import business.EmployeeProjectBusiness;
import dao.EmployeeProjectDAO;
import dao.imp.EmployeeProjectDAOImp;

import java.util.Scanner;

public class EmployeeProjectBusinessImp implements EmployeeProjectBusiness {
    private final EmployeeProjectDAO employeeProjectDAO;

    public EmployeeProjectBusinessImp() {
        employeeProjectDAO = new EmployeeProjectDAOImp();
    }

    @Override
    public void assignEmployeeToProject(Scanner scanner) {
        System.out.print("Nhập vào ID nhân viên: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vào ID dự án: ");
        int projectId = Integer.parseInt(scanner.nextLine());
        if(employeeProjectDAO.assignEmployeeToProject(employeeId, projectId)){
            System.out.println("Gán thành công");
        } else {
            System.err.println("Gán thất bại!");
        }
    }
}
