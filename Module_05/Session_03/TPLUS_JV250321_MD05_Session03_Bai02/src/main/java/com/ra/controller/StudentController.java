package com.ra.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/StudentController")
public class StudentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        req.setAttribute("name", name);
        req.setAttribute("age", age);
        req.setAttribute("address", address);

        req.getRequestDispatcher("studentInfo.jsp").forward(req, resp);
    }
}
