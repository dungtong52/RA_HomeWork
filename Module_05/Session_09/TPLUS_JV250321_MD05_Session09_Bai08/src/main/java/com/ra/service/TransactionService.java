package com.ra.service;

import com.ra.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    public void addTransaction(List<Transaction> transactionList, Transaction transaction) {
        transactionList.add(transaction);
    }

    public Transaction findTransactionById(int id, List<Transaction> transactionList){
        return transactionList.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void editTransaction(int id, List<Transaction> transactionList, Transaction transaction) {
        for (Transaction tr : transactionList) {
            if (tr.getId() == id) {
                tr.setDescription(transaction.getDescription());
                tr.setAmount(transaction.getAmount());
                tr.setType(transaction.isType());
                break;
            }
        }
    }

    public void deleteTransaction(int id, List<Transaction> transactionList) {
        transactionList.removeIf(tr -> tr.getId() == id);
    }
}
