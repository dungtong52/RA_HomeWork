package dao;

import entity.Student;

import java.util.List;

public interface StudentDAO {
    boolean addStudent(Student student);
    boolean existsByEmail(String email);
    List<Student> getAllStudents();
}
