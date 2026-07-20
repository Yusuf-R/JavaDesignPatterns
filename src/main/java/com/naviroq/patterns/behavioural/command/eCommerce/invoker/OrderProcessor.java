package com.naviroq.patterns.behavioural.command.eCommerce.invoker;

import com.naviroq.patterns.behavioural.command.eCommerce.task.Task;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private final List<Task> tasks = new ArrayList<>();
    private final List<Task> executedTasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean processOrder() {
        System.out.println("\n🔄 Processing Order...");
        System.out.println("========================================");

        for (Task task : tasks) {
            System.out.println("\n▶️  Executing: " + task.getDescription());
            task.execute();

            if (!task.isSuccessful()) {
                System.out.println("\n❌ Order FAILED at: " + task.getDescription());
                System.out.println("🔄 Initiating ROLLBACK...");
                rollbackAll();
                return false;
            }
            executedTasks.add(task);
        }

        System.out.println("\n✅ Order COMPLETED successfully!");
        System.out.println("========================================");
        return true;
    }

    private void rollbackAll() {
        System.out.println("------------------------------------------------");
        // Rollback in reverse order
        for (int i = executedTasks.size() - 1; i >= 0; i--) {
            Task task = executedTasks.get(i);
            System.out.println("↩️  Rolling back: " + task.getDescription());
            task.rollback();
        }
        System.out.println("------------------------------------------------");
        System.out.println("⚠️  All operations rolled back. Order cancelled.");
    }
}