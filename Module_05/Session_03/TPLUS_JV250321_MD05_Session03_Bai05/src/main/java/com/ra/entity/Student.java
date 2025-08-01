package com.ra.entity;

import com.ra.service.StudentService;

public class Student {
    private int id;
    private String name;
    private int age;
    private String address;

    public Student() {
    }

    public Student(String name, int age, String address) {
        this.id = setStudentId();
        this.name = name;
        this.age = age;
        this.address = address;
    }
    // for Update
    public Student(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int setStudentId() {
        int max = 0;
        for (Student student : StudentService.studentList) {
            if (student.id > max) {
                max = student.id;
            }
        }
        return max + 1;
    }
}
