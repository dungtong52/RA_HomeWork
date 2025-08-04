package com.ra.controller;

import com.ra.model.Account;
import com.ra.model.AccountSession;
import com.ra.model.enumClass.RoleEnum;
import com.ra.service.AccountService;
import com.ra.service.imp.AccountServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private final AccountService accountService;

    public AccountController() {
        accountService = new AccountServiceImp();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Account account = accountService.getAccountLogin(username, password);
        if (account != null) {
            AccountSession.currentAcount = account;
            if (account.getRole() != RoleEnum.CUSTOMER) {
                resp.sendRedirect(req.getContextPath() + "/movie?action=list");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            String error = "Tài khoản không tồn tại!";
            req.setAttribute("error", error);
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }
}
