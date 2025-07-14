package dao;

import java.math.BigDecimal;

public interface AccountDAO {
    boolean transferFunds(int from_account, int to_account, BigDecimal amount);
}
