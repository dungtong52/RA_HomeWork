package com.ra.controller;

import com.ra.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @GetMapping
    public String showAll(Model model){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Nguyễn Văn A", 20, "CNTT1", "a.nguyen@example.com", "Hà Nội", "0123456789"));
        students.add(new Student(2, "Trần Thị B", 21, "CNTT2", "b.tran@example.com", "Hồ Chí Minh", "0987654321"));
        students.add(new Student(3, "Lê Văn C", 22, "CNTT1", "c.le@example.com", "Đà Nẵng", "0912345678"));
        students.add(new Student(4, "Phạm Thị D", 19, "CNTT3", "d.pham@example.com", "Hải Phòng", "0909876543"));

        model.addAttribute("students", students);
        return "list";
    }
}
