package business.imp;

import business.AccountBusiness;
import dao.AccountDAO;
import business.PaginationBusiness;
import dao.imp.AccountDAOImp;
import entity.Account;
import entity.PaginationResult;

public class AccountBusinessImp implements AccountBusiness, PaginationBusiness<Account> {
    private final AccountDAO accountDAO;

    public AccountBusinessImp() {
        accountDAO = new AccountDAOImp();
    }

    @Override
    public Account getAccountToLogin(String userName, String password) {
        return accountDAO.getAccountToLogin(userName, password);
    }

    @Override
    public boolean createAccount(Account account) {
        return accountDAO.createAccount(account);
    }

    @Override
    public Account getAccountById(int accountId) {
        return accountDAO.getAccountById(accountId);
    }

    @Override
    public boolean checkExistAccountName(String accountName) {
        return accountDAO.checkExistAccountName(accountName);
    }

    @Override
    public boolean checkExistEmpId(String empId) {
        return accountDAO.checkExistEmpId(empId);
    }

    @Override
    public boolean updateAccountStatus(Account account) {
        return accountDAO.updateAccountStatus(account);
    }

    @Override
    public Account getAccountByUserName(String userName) {
        return accountDAO.getAccountByUserName(userName);
    }

    @Override
    public Account getAccountByEmpName(String empName) {
        return accountDAO.getAccountByEmpName(empName);
    }

    @Override
    public PaginationResult<Account> getPaginationData(String key, int size, int currentPage) {
        if (key == null || key.trim().isEmpty()) {
            return accountDAO.getAllAccountPagination(size, currentPage);
        }
        return null;
    }
}
