package com.naviroq.patterns.behavioural.command.bankTransaction.commands.CreditCommand;

import com.naviroq.patterns.behavioural.command.bankTransaction.commands.AbstractTransaction;
import com.naviroq.patterns.behavioural.command.bankTransaction.domain.Account;

public class Credit extends AbstractTransaction {
    private final Account account;
    private final double amount;

    public Credit(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.credit(amount);
        markExecuted();
        System.out.printf("  ✓ [%s] Credited $%.2f to %s%n", id, amount, account.getAccountNumber());
    }

    @Override
    public void rollback() {
        if (!isExecuted()) return;
        account.debit(amount);
        System.out.printf("  ↩ [%s] ROLLED BACK credit of $%.2f from %s%n", id, amount, account.getAccountNumber());
    }

    @Override
    public String getDescription() {
        return String.format("CREDIT $%.2f to %s", amount, account.getAccountNumber());
    }
}