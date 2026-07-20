package com.naviroq.patterns.behavioural.command.bankTransaction.commands.TransferCommand;

import com.naviroq.patterns.behavioural.command.bankTransaction.commands.AbstractTransaction;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.CreditCommand.Credit;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.DebitCommand.Debit;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.TransactionCommand;
import com.naviroq.patterns.behavioural.command.bankTransaction.domain.Account;
import java.util.ArrayList;
import java.util.List;

public class Transfer extends AbstractTransaction {
    private final Account from;
    private final Account to;
    private final double amount;
    private final List<TransactionCommand> subCommands = new ArrayList<>();

    public Transfer(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.printf("  → [%s] Starting transfer $%.2f: %s → %s%n", id, amount, from.getAccountNumber(), to.getAccountNumber());

        // Step 1: Debit from source
        Debit debit = new Debit(from, amount);
        debit.execute();
        subCommands.add(debit);

        // Step 2: Credit to destination
        Credit credit = new Credit(to, amount);
        credit.execute();
        subCommands.add(credit);

        markExecuted();
        System.out.printf("  ✓ [%s] Transfer complete%n", id);
    }

    @Override
    public void rollback() {
        if (!isExecuted()) return;
        System.out.printf("  ↩ [%s] Rolling back transfer...%n", id);
        // Rollback in reverse order!
        for (int i = subCommands.size() - 1; i >= 0; i--) {
            subCommands.get(i).rollback();
        }
    }

    @Override
    public String getDescription() {
        return String.format("TRANSFER $%.2f: %s → %s", amount, from.getAccountNumber(), to.getAccountNumber());
    }
}