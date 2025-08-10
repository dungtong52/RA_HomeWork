package com.ra.service;

import com.ra.model.Student;
import com.ra.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.getAllStudent();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public boolean existEmail(String email) {
        return studentRepository.isExistEmail(email);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(long id) {
        return studentRepository.deleteStudent(id);
    }
}
