package com.ra.tplus_jv250321_md05_session01_bai03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/convert")
public class convertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long rate = Long.parseLong(req.getParameter("rate"));
        long usdCurrency = Long.parseLong(req.getParameter("usdCurrency"));

        long vndCurrency = rate * usdCurrency;

        req.setAttribute("rate", rate);
        req.setAttribute("usdCurrency", usdCurrency);
        req.setAttribute("vndCurrency", vndCurrency);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
