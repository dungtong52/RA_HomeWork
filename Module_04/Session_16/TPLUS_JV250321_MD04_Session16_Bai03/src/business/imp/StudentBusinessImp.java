package business.imp;

import business.StudentBusiness;
import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentBusinessImp implements StudentBusiness {
    private final StudentDAO studentDAO;

    public StudentBusinessImp() {
        studentDAO = new StudentDAOImp();
    }

    @Override
    public void createMultipleStudent() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Nguyen Van A", 20));
        studentList.add(new Student("Nguyen Van B", 23));
        studentList.add(new Student("Nguyen Van C", 19));

        boolean success = studentDAO.addListStudent(studentList);
        if (success) {
            System.out.println("Thêm thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm");
        }
    }

    @Override
    public void updateStudent(Scanner scanner) {
        System.out.print("Nhập ID sinh viên muốn cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());

        Student student = studentDAO.findStudentById(id);
        if (student == null) {
            System.err.println("Không tìm thấy sinh viên có ID: " + id);
        } else {
            student.inputData(scanner);
            boolean success = studentDAO.updateStudent(student);
            if (success) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Có lỗi trong quá trình cập nhật");
            }
        }

    }

    @Override
    public void deleteStudent(Scanner scanner) {
        System.out.print("Nhập tuổi để xóa những sinh viên có độ tuổi nhỏ hơn: ");
        int age = Integer.parseInt(scanner.nextLine());
        int deleteCount = studentDAO.deleteListStudentUnderAge(age);
        if (deleteCount > 0) {
            System.out.printf("Xóa thành công %d sinh viên có độ tuổi nhỏ hơn %d\n", deleteCount, age);
        } else {
            System.out.printf("Không tìm thấy sinh viên nào có tuổi nhỏ hơn %d\n", age);
        }
    }
}
