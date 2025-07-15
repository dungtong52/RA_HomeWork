package business;

import entity.Student;

import java.util.List;

public interface StudentBusiness {
    boolean addStudent(Student student);
    List<Student> getAllStudents();
}
