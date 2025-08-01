package com.ra.controller;

import com.ra.entity.Student;
import com.ra.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/UpdateStudentController")
public class UpdateStudentController extends HttpServlet {
    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            req.setAttribute("student", student);
        }

        req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String address = req.getParameter("address");

        Student student = new Student(id, name, age, address);
        studentService.updateStudent(id, student);
        resp.sendRedirect("/AllStudentController");
    }
}
