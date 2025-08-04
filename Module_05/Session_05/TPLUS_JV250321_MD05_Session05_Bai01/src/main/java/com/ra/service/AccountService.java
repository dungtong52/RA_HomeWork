package com.ra.service;

import com.ra.model.Account;

public interface AccountService {
    Account getAccountLogin(String username, String password);
}
