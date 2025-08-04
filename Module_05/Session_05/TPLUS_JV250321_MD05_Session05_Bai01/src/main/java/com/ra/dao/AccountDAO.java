package com.ra.dao;

import com.ra.model.Account;

public interface AccountDAO {
    Account getAccountLogin(String username, String password);
}
