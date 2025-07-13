package business.imp;

import business.AssignmentBusiness;
import dao.AssignmentDAO;
import dao.EmployeeDAO;
import dao.ProjectDAO;
import dao.imp.AssignmentDAOImp;
import dao.imp.EmployeeDAOImp;
import dao.imp.ProjectDAOImp;
import entity.Assignment;
import entity.Employee;
import entity.Project;

import java.util.List;
import java.util.Scanner;

public class AssignmentBusinessImp implements AssignmentBusiness {
    private final AssignmentDAO assignmentDAO = new AssignmentDAOImp();
    private final EmployeeDAO employeeDAO = new EmployeeDAOImp();
    private final ProjectDAO projectDAO = new ProjectDAOImp();

    @Override
    public void assignEmployeeToProject(Scanner scanner) {
        Assignment assignment = new Assignment();
        assignment.inputData(scanner);

        // Kiểm tra tồn tại
        Employee emp = employeeDAO.findById(assignment.getEmployeeId());
        Project proj = projectDAO.findById(assignment.getProjectId());

        if (emp == null) {
            System.err.println("Không tìm thấy nhân viên.");
            return;
        }
        if (proj == null) {
            System.err.println("Không tìm thấy dự án.");
            return;
        }

        boolean success = assignmentDAO.assignEmployeeToProject(assignment);
        if (success) {
            System.out.println("Gán nhân viên vào dự án thành công.");
        } else {
            System.err.println("Gán thất bại.");
        }
    }

    @Override
    public void showAllAssignments() {
        List<Assignment> list = assignmentDAO.getAllAssignments();
        if (list.isEmpty()) {
            System.out.println("Chưa có phân công nào.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
