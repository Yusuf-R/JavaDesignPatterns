package com.naviroq.patterns.behavioural.command.bankTransaction.invoker;

import com.naviroq.patterns.behavioural.command.bankTransaction.commands.TransactionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TransactionManager {
    private final List<TransactionCommand> history = new ArrayList<>();
    private final List<TransactionCommand> failedTransactions = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final boolean autoRollbackOnFailure;

    public TransactionManager() {
        this(true);
    }

    public TransactionManager(boolean autoRollbackOnFailure) {
        this.autoRollbackOnFailure = autoRollbackOnFailure;
    }

    /**
     * Execute a single command with retry logic
     */
    public void execute(TransactionCommand command) {
        System.out.println("\n┌────────────────────────────────────────");
        System.out.println("│ EXECUTING: " + command.getDescription());
        System.out.println("│ ID: " + command.getId());
        System.out.println("└────────────────────────────────────────");

        int maxRetries = 3;
        boolean success = false;

        for (int attempt = 1; attempt <= maxRetries && !success; attempt++) {
            try {
                command.execute();
                history.add(command);
                success = true;
                System.out.println("│ ✅ SUCCESS");
            } catch (Exception e) {
                System.out.printf("│ ⚠️ ATTEMPT %d FAILED: %s%n", attempt, e.getMessage());
                if (attempt == maxRetries) {
                    failedTransactions.add(command);
                    System.out.println("│ ❌ MAX RETRIES REACHED");
                    if (autoRollbackOnFailure) {
                        rollbackRecent();
                    }
                } else {
                    // Exponential backoff
                    try {
                        Thread.sleep(100 * attempt);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    /**
     * Execute multiple commands as an atomic batch
     */
    public void executeBatch(List<TransactionCommand> commands) {
        System.out.println("\n╔════════════════════════════════════════");
        System.out.println("║ ATOMIC BATCH TRANSACTION");
        System.out.println("║ Commands: " + commands.size());
        System.out.println("╚════════════════════════════════════════");

        List<TransactionCommand> executed = new ArrayList<>();

        try {
            for (TransactionCommand cmd : commands) {
                cmd.execute();
                executed.add(cmd);
            }
            history.addAll(executed);
            System.out.println("║ ✅ BATCH COMMITTED SUCCESSFULLY");
        } catch (Exception e) {
            System.out.println("║ ❌ BATCH FAILED: " + e.getMessage());
            System.out.println("║ ↩ ROLLING BACK " + executed.size() + " COMPLETED OPERATIONS...");
            // Rollback in reverse
            for (int i = executed.size() - 1; i >= 0; i--) {
                executed.get(i).rollback();
            }
            System.out.println("║ ↩ ROLLBACK COMPLETE");
        }
    }

    /**
     * Async execution with Future
     */
    public Future<?> executeAsync(TransactionCommand command) {
        return executor.submit(() -> execute(command));
    }

    /**
     * Rollback the most recent bankTransaction
     */
    public void rollbackRecent() {
        if (history.isEmpty()) {
            System.out.println("│ ℹ️ Nothing to rollback");
            return;
        }
        TransactionCommand last = history.remove(history.size() - 1);
        System.out.println("│ ↩ Rolling back: " + last.getDescription());
        last.rollback();
    }

    /**
     * Full audit log
     */
    public void printAuditLog() {
        System.out.println("\n╔════════════════════════════════════════");
        System.out.println("║         AUDIT LOG");
        System.out.println("╠════════════════════════════════════════");
        System.out.println("║ Completed Transactions: " + history.size());
        for (TransactionCommand cmd : history) {
            System.out.printf("║   [%s] %s | %s%n",
                    cmd.getId(), cmd.getTimestamp(), cmd.getDescription());
        }
        if (!failedTransactions.isEmpty()) {
            System.out.println("╠════════════════════════════════════════");
            System.out.println("║ Failed Transactions: " + failedTransactions.size());
            for (TransactionCommand cmd : failedTransactions) {
                System.out.printf("║   [%s] %s%n", cmd.getId(), cmd.getDescription());
            }
        }
        System.out.println("╚════════════════════════════════════════");
    }

    public void shutdown() {
        executor.shutdown();
    }
}