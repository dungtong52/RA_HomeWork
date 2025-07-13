package presentation;

import business.AssignmentBusiness;
import business.EmployeeBusiness;
import business.ProjectBusiness;
import business.imp.AssignmentBusinessImp;
import business.imp.EmployeeBusinessImp;
import business.imp.ProjectBusinessImp;
import validation.Validation;

import java.util.Scanner;

public class Main {
    private final EmployeeBusiness employeeBusiness;
    private final ProjectBusiness projectBusiness;
    private final AssignmentBusiness assignmentBusiness;

    public Main() {
        employeeBusiness = new EmployeeBusinessImp();
        projectBusiness = new ProjectBusinessImp();
        assignmentBusiness = new AssignmentBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main mainApp = new Main();

        do {
            System.out.println("\n********** MENU QUẢN LÝ NHÂN VIÊN - DỰ ÁN **********");
            System.out.println(
                    "1. Thêm nhân viên\n" +
                            "2. Thêm dự án\n" +
                            "3. Gán nhân viên vào dự án\n" +
                            "4. Hiển thị danh sách nhân viên\n" +
                            "5. Hiển thị danh sách dự án\n" +
                            "6. Hiển thị danh sách phân công\n" +
                            "7. Cập nhật lương nhân viên\n" +
                            "8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();

            if (Validation.isIntegerInRange(choice, 1, 8)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> mainApp.employeeBusiness.createEmployee(scanner);
                    case 2 -> mainApp.projectBusiness.createProject(scanner);
                    case 3 -> mainApp.assignmentBusiness.assignEmployeeToProject(scanner);
                    case 4 -> mainApp.employeeBusiness.showAllEmployees();
                    case 5 -> mainApp.projectBusiness.showAllProjects();
                    case 6 -> mainApp.assignmentBusiness.showAllAssignments();
                    case 7 -> mainApp.employeeBusiness.updateEmployeeSalary(scanner);
                    case 8 -> {
                        System.out.println("Thoát chương trình.");
                        System.exit(0);
                    }
                }
            } else {
                System.err.println("Vui lòng nhập số từ 1 đến 8.");
            }
        } while (true);
    }
}
