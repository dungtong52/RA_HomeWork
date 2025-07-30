package com.ra.tplus_jv250321_md05_session01_bai08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registerTicket")
public class RegisterTicket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRegister(req, resp);
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String className = request.getParameter("class");
        String vehicleType = request.getParameter("type");
        String licensePlate = request.getParameter("licensePlate");

        request.setAttribute("fullName", fullName);
        request.setAttribute("className", className);
        request.setAttribute("vehicleType", vehicleType);
        request.setAttribute("licensePlate", licensePlate);

        request.getRequestDispatcher("resultPage.jsp").forward(request, response);

    }
}
