package dao;

import entity.Account;
import entity.PaginationResult;

public interface AccountDAO {
    Account getAccountToLogin(String userName, String password);

    PaginationResult<Account> getAllAccountPagination(int size, int currentPage);

    boolean createAccount(Account account);

    Account getAccountById(int accountId);

    boolean checkExistAccountName(String accountName);

    boolean checkExistEmpId(String empId);

    boolean updateAccountStatus(Account account);

    Account getAccountByUserName(String userName);

    Account getAccountByEmpName(String empName);

}
