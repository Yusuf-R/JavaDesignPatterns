package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class HasCoinState implements VendingState {
    private VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("   ❌ Coin already inserted.");
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("   📦 Product selected: " + product);

        if (machine.getStock() <= 0) {
            System.out.println("   ❌ Out of stock.");
            machine.setState(machine.getOutOfStockState());
            return;
        }

        System.out.println("   🔄 Dispensing product...");
        machine.setState(machine.getDispensingState());
        machine.dispense(); // Trigger dispense automatically
    }

    @Override
    public void dispense() {
        System.out.println("   ❌ Please select a product first.");
    }

    @Override
    public void refund() {
        System.out.println("   ↩️ Refunding $" + machine.getCurrentAmount() + " to user.");
        machine.resetAmount();
        machine.setState(machine.getIdleState());
    }

    @Override
    public String getStateName() {
        return "HAS_COIN (Coin inserted, waiting for selection)";
    }
}