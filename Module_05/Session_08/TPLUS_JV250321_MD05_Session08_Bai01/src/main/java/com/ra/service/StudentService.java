package com.ra.service;

import com.ra.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    Student getStudentById(long id);

    boolean existEmail(String email);

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(long id);
}
