package dao;

import entity.Student;

import java.util.List;

public interface StudentDAO {
    boolean addListStudent(List<Student> studentList);

    Student findStudentById(int id);

    boolean updateStudent(Student student);

    int deleteListStudentUnderAge(int age);
}
