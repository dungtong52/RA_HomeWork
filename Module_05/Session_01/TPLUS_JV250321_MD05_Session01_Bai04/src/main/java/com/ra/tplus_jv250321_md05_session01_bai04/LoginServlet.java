package com.ra.tplus_jv250321_md05_session01_bai04;

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

        if (username.equals("NguyenVanA") && password.equals("matkhauco8kytu")) {
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
        } else {
            String error = "Invalid username or password";
            req.setAttribute("error", error);

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }
}
