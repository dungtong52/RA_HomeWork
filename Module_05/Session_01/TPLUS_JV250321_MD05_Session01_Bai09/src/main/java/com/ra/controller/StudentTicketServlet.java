package com.ra.controller;

import com.ra.business.StudentBusiness;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/StudentTicketServlet")
public class StudentTicketServlet extends HttpServlet {
    private final StudentBusiness studentBusiness = new StudentBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentBusiness.getStudentList());
        req.getRequestDispatcher("listStudent.jsp").forward(req, resp);
    }
}
