package com.naviroq.patterns.behavioural.command.bankTransaction.commands.DebitCommand;

import com.naviroq.patterns.behavioural.command.bankTransaction.commands.AbstractTransaction;
import com.naviroq.patterns.behavioural.command.bankTransaction.domain.Account;

public class Debit extends AbstractTransaction {
    private final Account account;
    private final double amount;
    private double previousBalance;

    public Debit(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        previousBalance = account.getBalance();
        account.debit(amount);
        markExecuted();
        System.out.printf("  ✓ [%s] Debited $%.2f from %s%n", id, amount, account.getAccountNumber());
    }

    @Override
    public void rollback() {
        if (!isExecuted()) return;
        // Restore by crediting back
        account.credit(amount);
        System.out.printf("  ↩ [%s] ROLLED BACK debit of $%.2f to %s (restored $%.2f)%n",
                id, amount, account.getAccountNumber(), previousBalance);
    }

    @Override
    public String getDescription() {
        return String.format("DEBIT $%.2f from %s", amount, account.getAccountNumber());
    }
}