package com.ra.tplus_jv250321_md05_session01_bai01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/timeNow")
public class TimeNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentTime = now.format(formatter);

        req.setAttribute("currentTime", currentTime);
        req.getRequestDispatcher("timeNow.jsp").forward(req, resp);
    }
}