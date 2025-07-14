package entity;

import java.math.BigDecimal;
import java.util.Scanner;

public class Account {
    private int id;
    private BigDecimal balance;

    public Account() {
    }

    public Account(int id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Balance: %,.2f\n", this.id, this.balance);
    }
}
