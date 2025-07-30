package com.ra.demotplus_jv250321_md05_session01_bai02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 1;
        String name = "Huấn";
        String email = "huanrose@gmail.com";
        int age = 18;

        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("age", age);

        req.getRequestDispatcher("userDisplay.jsp").forward(req, resp);
    }
}
