package com.naviroq.patterns.behavioural.state.vendingMachine;

import com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates.*;

public class VendingMachine {
    private VendingState currentState;
    private final VendingState idleState;
    private final VendingState hasCoinState;
    private final VendingState dispensingState;
    private final VendingState outOfStockState;
    private final VendingState maintenanceState;


    private int stock = 5;
    private int currentAmount = 0;

    public VendingMachine() {
        // Create all states
        idleState = new IdleState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);
        outOfStockState = new OutOfStockState(this);
        maintenanceState = new MaintenanceState(this);

        // Start in idle state
        currentState = idleState;
    }

    // --- Admin Methods (NEW) ---
    public void enterMaintenance() {
        System.out.println("   🔧 Entering maintenance mode...");
        setState(maintenanceState);
    }

    public void exitMaintenance() {
        if (currentState != maintenanceState) {
            System.out.println("   ❌ Not currently in maintenance mode.");
            return;
        }
        System.out.println("   ✅ Exiting maintenance mode.");
        if (stock <= 0) {
            System.out.println("   ⚠️ Stock is still empty. Set to OutOfStock.");
            setState(outOfStockState);
        } else {
            setState(idleState);
        }
    }

    public void restock(int quantity) {
        if (currentState != maintenanceState) {
            System.out.println("   ❌ Can only restock in maintenance mode.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("   ❌ Quantity must be positive.");
            return;
        }
        stock += quantity;
        System.out.println("   📦 Restocked " + quantity + " items. NewState stock: " + stock);
    }

    public void collectCash() {
        if (currentState != maintenanceState) {
            System.out.println("   ❌ Can only collect cash in maintenance mode.");
            return;
        }
        int cash = currentAmount;
        if (cash <= 0) {
            System.out.println("   💰 No cash to collect.");
            return;
        }
        System.out.println("   💰 Collected $" + cash + " from machine.");
        resetAmount();
    }


    // --- State Management ---
    public void setState(VendingState state) {
        this.currentState = state;
        System.out.println("   🔄 State changed to: " + state.getStateName());
    }

    public VendingState getIdleState() {
        return idleState;
    }

    public VendingState getHasCoinState() {
        return hasCoinState;
    }

    public VendingState getDispensingState() {
        return dispensingState;
    }

    public VendingState getOutOfStockState() {
        return outOfStockState;
    }

    public VendingState getMaintenanceState() {
        return maintenanceState;
    }

    // --- Context Data ---
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int amount) {
        this.currentAmount = amount;
    }

    public void addAmount(int amount) {
        this.currentAmount += amount;
    }

    public void resetAmount() {
        this.currentAmount = 0;
    }

    public void reduceStock() {
        if (stock > 0) stock--;
    }

    // --- Delegate to current state ---
    public void insertCoin() {
        currentState.insertCoin();
    }

    public void selectProduct(String product) {
        currentState.selectProduct(product);
    }

    public void dispense() {
        currentState.dispense();
    }

    public void refund() {
        currentState.refund();
    }

    public void showStatus() {
        System.out.println("\n📊 Vending Machine Status:");
        System.out.println("   State: " + currentState.getStateName());
        System.out.println("   Stock: " + stock + " items");
        System.out.println("   Current Amount: $" + currentAmount);
    }
}
