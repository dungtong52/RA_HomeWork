package com.ra.controller;

import com.ra.model.Student;
import com.ra.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/student"})
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showAllStudent(Model model) {
        List<Student> studentList = studentService.getAllStudent();
        model.addAttribute("students", studentList);
        return "list-student";
    }

    @GetMapping("/add")
    public String initAddStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-student";
        }
        boolean addSuccess = studentService.addStudent(student);
        if (!addSuccess) {
            redirectAttributes.addFlashAttribute("errorMsg", "Thêm sinh viên thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm sinh viên thành công");
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String initEditStudent(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            model.addAttribute("errorMsg", "Không tồn tại sinh viên này");
            return "list-student";
        } else {
            model.addAttribute("student", student);
            return "update-student";
        }
    }

    @PostMapping("/edit")
    public String editStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "update-student";
        }
        boolean updateSuccess = studentService.updateStudent(student);
        if (!updateSuccess) {
            redirectAttributes.addFlashAttribute("errorMsg", "Sửa sinh viên thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa sinh viên thành công");
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        boolean deleteSuccess = studentService.deleteStudent(id);
        if (!deleteSuccess) {
            redirectAttributes.addFlashAttribute("errorMsg", "Xóa sinh viên thất bại");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa sinh viên thành công");
        }
        return "redirect:/";
    }
}
