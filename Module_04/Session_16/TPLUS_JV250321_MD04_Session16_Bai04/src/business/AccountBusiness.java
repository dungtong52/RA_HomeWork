package business;

import java.math.BigDecimal;

public interface AccountBusiness {
    void transferFunds(int from_account, int to_account, BigDecimal amount);
}
