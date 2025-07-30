package com.ra.business;

import com.ra.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentBusiness {
    private static List<Student> studentList = new ArrayList<>();

    public StudentBusiness() {
        studentList.add(new Student("Nguyen Van A", "1A1", "Xe đạp", "1A1-1234"));
        studentList.add(new Student("Nguyen Van B", "1A2", "Xe máy", "1A2-4321"));
        studentList.add(new Student("Nguyen Van C", "1A3", "Xe oto", "1A3-1432"));
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public List<Student> getStudentList(){
        return studentList;
    }
}
