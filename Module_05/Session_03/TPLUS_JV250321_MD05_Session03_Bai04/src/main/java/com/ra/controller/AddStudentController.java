package com.ra.controller;

import com.ra.entity.Student;
import com.ra.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddStudentController")
public class AddStudentController extends HttpServlet {
    StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Student student = new Student(name, Integer.parseInt(age), address);
        studentService.addStudent(student);

        resp.sendRedirect("/AllStudentController");
    }
}
