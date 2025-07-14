import business.AccountBusiness;
import business.imp.AccountBusinessImp;

import java.math.BigDecimal;

public class Main {
    private static final AccountBusiness accountBusiness = new AccountBusinessImp();

    public static void main(String[] args) {
        accountBusiness.transferFunds(1, 2, new BigDecimal(10000000));
    }
}
