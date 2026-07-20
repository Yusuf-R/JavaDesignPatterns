package com.naviroq.patterns.behavioural.command.bankTransaction.domain;

public class Account {
    private final String accountNumber;
    private final String ownerName;
    private double balance;
    private boolean frozen = false;

    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public synchronized void debit(double amount) {
        if (frozen) throw new IllegalStateException("Account frozen");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
    }

    public synchronized void credit(double amount) {
        if (frozen) throw new IllegalStateException("Account frozen");
        balance += amount;
    }

    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public String toString() {
        return String.format("Account[%s | %s | $%.2f | %s]",
                accountNumber, ownerName, balance, frozen ? "FROZEN" : "ACTIVE");
    }
}