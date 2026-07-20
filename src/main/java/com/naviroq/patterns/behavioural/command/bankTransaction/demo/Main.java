package com.naviroq.patterns.behavioural.command.bankTransaction.demo;

import com.naviroq.patterns.behavioural.command.bankTransaction.commands.CreditCommand.Credit;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.DebitCommand.Debit;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.TransactionCommand;
import com.naviroq.patterns.behavioural.command.bankTransaction.invoker.TransactionManager;
import com.naviroq.patterns.behavioural.command.bankTransaction.commands.TransferCommand.Transfer;
import com.naviroq.patterns.behavioural.command.bankTransaction.domain.Account;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Setup accounts
        Account alice = new Account("ACC-001", "Alice Johnson", 1000.00);
        Account bob = new Account("ACC-002", "Bob Smith", 500.00);
        Account charlie = new Account("ACC-003", "Charlie Brown", 200.00);

        System.out.println("════════════════════════════════════════");
        System.out.println("     BANKING TRANSACTION SYSTEM");
        System.out.println("════════════════════════════════════════");
        System.out.println("Initial State:");
        System.out.println("  " + alice);
        System.out.println("  " + bob);
        System.out.println("  " + charlie);

        TransactionManager manager = new TransactionManager(true);

        // ─── Scenario 1: Simple Transfer ───
        System.out.println("\n▶ SCENARIO 1: Alice sends $300 to Bob");
        manager.execute(new Transfer(alice, bob, 300.00));

        // ─── Scenario 2: Batch Salary Payments ───
        System.out.println("\n▶ SCENARIO 2: Batch - Alice pays Bob & Charlie");
        List<TransactionCommand> salaryBatch = Arrays.asList(
                new Transfer(alice, bob, 200.00),
                new Transfer(alice, charlie, 150.00)
        );
        manager.executeBatch(salaryBatch);

        // ─── Scenario 3: Failed Transfer (insufficient funds) ───
        System.out.println("\n▶ SCENARIO 3: Charlie tries to send $500 to Alice (will fail)");
        manager.execute(new Transfer(charlie, alice, 500.00));

        // ─── Scenario 4: Complex multi-step with rollback ───
        System.out.println("\n▶ SCENARIO 4: Complex operation - then rollback");
        manager.execute(new Debit(bob, 100.00));
        manager.execute(new Credit(alice, 100.00));
        System.out.println("\n  ↩ Rolling back last operation...");
        manager.rollbackRecent(); // Undoes Alice's credit

        // ─── Final State ───
        System.out.println("\n════════════════════════════════════════");
        System.out.println("     FINAL STATE");
        System.out.println("════════════════════════════════════════");
        System.out.println("  " + alice);
        System.out.println("  " + bob);
        System.out.println("  " + charlie);

        // Audit
        manager.printAuditLog();

        manager.shutdown();
    }
}