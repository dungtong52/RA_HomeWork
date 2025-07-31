package com.ra;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        if (username.equals("admin") && password.equals("123456")) {
            String success = "Đăng nhập thành công";
            req.setAttribute("success", success);
        } else {
            String error = "Đăng nhập thất bại";
            req.setAttribute("error", error);
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
