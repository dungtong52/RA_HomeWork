package com.ra.tplus_jv250321_md05_session01_bai05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|vn)$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        boolean success = true;

        if (username.isBlank()) {
            String errorName = "Username is required";
            req.setAttribute("errorName", errorName);
            success = false;
        }
        if (!email.matches(emailRegex)) {
            String errorEmail = "Invalid Email";
            req.setAttribute("errorEmail", errorEmail);
            success = false;
        }
        if (!password.matches(passwordRegex)) {
            String errorPassword = "Invalid Password";
            req.setAttribute("errorPassword", errorPassword);
            success = false;
        }
        if (!confirmPassword.matches(passwordRegex) || !confirmPassword.equals(password)) {
            String errorConfirmPassword = "Confirm Password does not match";
            req.setAttribute("errorConfirmPassword", errorConfirmPassword);
            success = false;
        }
        if (success) {
            String message = "Registed Successfully";
            req.setAttribute("success", message);
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
