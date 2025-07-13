package business.imp;

import business.StudentBusiness;
import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentBusinessImp implements StudentBusiness {
    private final StudentDAO studentDAO = new StudentDAOImp();

    @Override
    public void addStudent(Scanner scanner) {
        Student student = new Student();
        student.inputData(scanner);

        if (studentDAO.findByEmail(student.getEmail()) != null) {
            System.err.println("Email đã tồn tại. Không thể thêm sinh viên.");
            return;
        }

        boolean result = studentDAO.addStudent(student);
        if (result) {
            System.out.println("Thêm sinh viên thành công.");
        } else {
            System.err.println("Thêm sinh viên thất bại.");
        }
    }

    @Override
    public void showAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Chưa có sinh viên nào.");
            return;
        }
        students.forEach(System.out::println);
    }
}
