package com.naviroq.patterns.behavioural.command.eCommerce.task.createShippingLabelTask;

import com.naviroq.patterns.behavioural.command.eCommerce.OrderContext;
import com.naviroq.patterns.behavioural.command.eCommerce.task.Task;

public class CreateShippingLabel implements Task {
    private final OrderContext context;
    private boolean success = false;

    public CreateShippingLabel(OrderContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        System.out.println("   📬 Creating shipping label for: " + context.getShippingAddress());
        // Simulate shipping failure (10% chance)
        if (Math.random() > 0.1) {
            context.setShippingLabelCreated(true);
            success = true;
            System.out.println("   ✅ Shipping label created.");
        } else {
            success = false;
            System.out.println("   ❌ Shipping label FAILED (address invalid).");
        }
    }

    @Override
    public void rollback() {
        if (context.isShippingLabelCreated()) {
            System.out.println("   ↩️  Voiding shipping label.");
            context.setShippingLabelCreated(false);
        }
    }

    @Override
    public String getDescription() {
        return "Create Shipping Label (" + context.getShippingAddress() + ")";
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}
