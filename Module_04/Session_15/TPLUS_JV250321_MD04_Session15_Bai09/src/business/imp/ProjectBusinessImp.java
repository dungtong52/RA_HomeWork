package business.imp;

import business.ProjectBusiness;
import dao.ProjectDAO;
import dao.imp.ProjectDAOImp;
import entity.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectBusinessImp implements ProjectBusiness {
    private final ProjectDAO projectDAO = new ProjectDAOImp();

    @Override
    public void createProject(Scanner scanner) {
        Project project = new Project();
        project.inputData(scanner);

        Project existing = projectDAO.findByName(project.getName());
        if (existing != null) {
            System.err.println("Dự án đã tồn tại.");
            return;
        }

        boolean success = projectDAO.addProject(project);
        if (success) {
            System.out.println("Thêm dự án thành công.");
        } else {
            System.err.println("Thêm dự án thất bại.");
        }
    }

    @Override
    public void showAllProjects() {
        List<Project> list = projectDAO.getAllProjects();
        if (list.isEmpty()) {
            System.out.println("Không có dự án nào.");
            return;
        }
        list.forEach(System.out::println);
    }
}
