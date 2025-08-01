package com.ra.service;

import com.ra.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static List<Student> studentList = new ArrayList<>();

    public List<Student> getAllStudents() {
        return studentList;
    }

    public Student getStudentById(int id){
        return studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void updateStudent(int id, Student student) {
        studentList.stream()
                .filter(student1 -> student1.getId() == id)
                .findFirst()
                .ifPresent(student1 -> {
                    student1.setName(student.getName());
                    student1.setAge(student.getAge());
                    student1.setAddress(student.getAddress());
                });
    }

    public void deleteStudent(int id) {
        studentList.removeIf(student -> student.getId() == id);
    }
}
