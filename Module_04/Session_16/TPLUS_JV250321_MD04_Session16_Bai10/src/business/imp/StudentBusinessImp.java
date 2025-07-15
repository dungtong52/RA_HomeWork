package business.imp;

import business.StudentBusiness;
import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import entity.Student;

import java.util.List;

public class StudentBusinessImp implements StudentBusiness {
    private final StudentDAO studentDAO;

    public StudentBusinessImp() {
        studentDAO = new StudentDAOImp();
    }

    @Override
    public boolean addStudent(Student student) {
        if (studentDAO.existsByEmail(student.getEmail())) {
            System.out.println("Email đã tồn tại!");
            return false;
        }
        return studentDAO.addStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
