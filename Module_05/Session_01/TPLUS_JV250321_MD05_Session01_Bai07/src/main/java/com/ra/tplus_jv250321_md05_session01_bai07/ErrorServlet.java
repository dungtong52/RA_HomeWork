package com.ra.tplus_jv250321_md05_session01_bai07;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/result")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int number1 = Integer.parseInt(req.getParameter("number1"));
            int number2 = Integer.parseInt(req.getParameter("number2"));

            float result = number1 / number2;
            req.setAttribute("result", result);
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errrorMessage", "Xảy ra lỗi: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
