package dao;

import entity.Account;

public interface AccountDAO {
    Account getAccountByUserName(String userName, String password);
}
