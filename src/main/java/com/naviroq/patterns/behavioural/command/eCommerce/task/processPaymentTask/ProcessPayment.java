package com.naviroq.patterns.behavioural.command.eCommerce.task.processPaymentTask;

import com.naviroq.patterns.behavioural.command.eCommerce.task.Task;
import com.naviroq.patterns.behavioural.command.eCommerce.OrderContext;

public class ProcessPayment implements Task {
    private final OrderContext context;
    private boolean success = false;

    public ProcessPayment(OrderContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        System.out.println("   💳 Charging card: $" + context.getAmount());
        // Simulate payment failure (20% chance)
        if (Math.random() > 0.2) {
            context.setPaymentCharged(true);
            success = true;
            System.out.println("   ✅ Payment approved.");
        } else {
            success = false;
            System.out.println("   ❌ Payment FAILED (insufficient funds).");
        }
    }

    @Override
    public void rollback() {
        if (context.isPaymentCharged()) {
            System.out.println("   ↩️  Refunding: $" + context.getAmount());
            context.setPaymentCharged(false);
        }
    }

    @Override
    public String getDescription() {
        return "Process Payment ($" + context.getAmount() + ")";
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}
