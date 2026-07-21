package com.naviroq.patterns.behavioural.state.vendingMachine.demo;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class Main {
    public static void main(String[] args) {
        System.out.println("🧃 ====== VENDING MACHINE (State Pattern) ======\n");

        VendingMachine machine = new VendingMachine();
        machine.showStatus();

        // 1. Sell all items
        System.out.println("\n--- Step 1: Sell all stock ---");
        for (int i = 0; i < 5; i++) {
            machine.insertCoin();
            machine.selectProduct("Soda");
            machine.showStatus();
        }

        // 2. Try to buy when empty (OutOfStockState)
        System.out.println("\n--- Step 2: Try to buy when empty ---");
        machine.insertCoin(); // Should fail
        machine.selectProduct("Chips"); // Should fail

        // 3. Enter Maintenance
        System.out.println("\n--- Step 3: Enter Maintenance ---");
        machine.enterMaintenance();
        machine.showStatus();

        // 4. Restock
        System.out.println("\n--- Step 4: Restock ---");
        machine.restock(10);
        machine.showStatus();

        // 5. Collect cash
        System.out.println("\n--- Step 5: Collect Cash ---");
        machine.collectCash();
        machine.showStatus();

        // 6. Exit Maintenance
        System.out.println("\n--- Step 6: Exit Maintenance ---");
        machine.exitMaintenance();
        machine.showStatus();

        // 7. Normal operation resumes
        System.out.println("\n--- Step 7: Normal operation resumes ---");
        machine.insertCoin();
        machine.selectProduct("Soda");
        machine.showStatus();

        System.out.println("\n✅ Demo Complete");
    }
}
