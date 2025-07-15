package business.imp;

import business.TransactionBusiness;
import dao.TransactionDAO;
import dao.imp.TransactionDAOImp;
import entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class TransactionBusinessImp implements TransactionBusiness {
    private final TransactionDAO transactionDAO;

    public TransactionBusinessImp() {
        transactionDAO = new TransactionDAOImp();
    }

    @Override
    public boolean transferMoney(int fromAccountId, int toAccountId, BigDecimal amount) {
        return transactionDAO.transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public List<Transaction> listTransactions(int accountId) {
        return transactionDAO.listTransactions(accountId);
    }
}
