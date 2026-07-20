package com.naviroq.patterns.behavioural.command.eCommerce.task.reserveInventoryTask;

import com.naviroq.patterns.behavioural.command.eCommerce.task.Task;
import com.naviroq.patterns.behavioural.command.eCommerce.OrderContext;

public class ReserveInventory implements Task {
    private final OrderContext context;
    private boolean success = false;

    public ReserveInventory(OrderContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        System.out.println("   📦 Reserving inventory: " + context.getQuantity() + "x " + context.getItem());
        // Simulate success (90% of the time)
        if (Math.random() > 0.1) {
            context.setInventoryReserved(true);
            success = true;
            System.out.println("   ✅ Inventory reserved.");
        } else {
            success = false;
            System.out.println("   ❌ Inventory reservation FAILED (out of stock).");
        }
    }

    @Override
    public void rollback() {
        if (context.isInventoryReserved()) {
            System.out.println("   ↩️  Releasing inventory: " + context.getQuantity() + "x " + context.getItem());
            context.setInventoryReserved(false);
        }
    }

    @Override
    public String getDescription() {
        return "Reserve Inventory (" + context.getQuantity() + "x " + context.getItem() + ")";
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}