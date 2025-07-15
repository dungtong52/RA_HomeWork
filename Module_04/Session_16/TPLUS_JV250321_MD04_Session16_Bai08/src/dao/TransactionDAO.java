package dao;

import entity.Account;
import entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDAO {
    boolean transferMoney(int fromAccountId, int toAccountId, BigDecimal amount);

    List<Transaction> listTransactions(int accountId);
}
