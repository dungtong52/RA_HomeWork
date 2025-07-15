package business.imp;

import business.AccountBusiness;
import dao.AccountDAO;
import dao.imp.AccountDAOImp;
import entity.Account;

public class AccountBusinessImp implements AccountBusiness {
    private final AccountDAO accountDAO;

    public AccountBusinessImp() {
        accountDAO = new AccountDAOImp();
    }

    @Override
    public Account getAccountInfo(int accountId) {
        return accountDAO.getAccountInfo(accountId);
    }
}
