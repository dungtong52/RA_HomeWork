package business.imp;

import business.AccountBusiness;
import dao.AccountDAO;
import dao.imp.AccountDAOImp;

import java.math.BigDecimal;

public class AccountBusinessImp implements AccountBusiness {
    private final AccountDAO accountDAO;

    public AccountBusinessImp() {
        accountDAO = new AccountDAOImp();
    }

    @Override
    public void transferFunds(int from_account, int to_account, BigDecimal amount) {
        boolean success = accountDAO.transferFunds(from_account, to_account, amount);
        if(success){
            System.out.println("Chuyển tiền thành công");
        } else {
            System.err.println("Chuyển tiền không thành công!");
        }
    }
}
