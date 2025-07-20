package business;

import entity.Account;
import entity.PaginationResult;

public interface AccountBusiness {
    Account getAccountToLogin(String userName, String password);

    boolean createAccount(Account account);

    Account getAccountById(int accountId);

    boolean checkExistAccountName(String accountName);

    boolean checkExistEmpId(String empId);

    boolean updateAccountStatus(Account account);

    Account getAccountByUserName(String userName);

    Account getAccountByEmpName(String empName);
}
