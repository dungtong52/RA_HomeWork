package com.ra.service.imp;

import com.ra.dao.AccountDAO;
import com.ra.dao.imp.AccountDAOImp;
import com.ra.model.Account;
import com.ra.service.AccountService;

public class AccountServiceImp implements AccountService {
    private final AccountDAO accountDAO;

    public AccountServiceImp() {
        accountDAO = new AccountDAOImp();
    }

    @Override
    public Account getAccountLogin(String username, String password) {
        return accountDAO.getAccountLogin(username, password);
    }
}
