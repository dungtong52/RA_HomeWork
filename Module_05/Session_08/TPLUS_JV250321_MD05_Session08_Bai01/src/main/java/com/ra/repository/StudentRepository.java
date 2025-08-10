package com.ra.repository;

import com.ra.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudent();

    Student getStudentById(long id);

    boolean isExistEmail(String email);

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(long id);
}
