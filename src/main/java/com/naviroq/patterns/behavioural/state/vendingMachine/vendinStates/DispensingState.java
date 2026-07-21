package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class DispensingState implements VendingState {
    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("   ❌ Dispensing in progress. Please wait.");
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("   ❌ Dispensing in progress. Please wait.");
    }

    @Override
    public void dispense() {
        System.out.println("   🍬 Dispensing product...");
        machine.reduceStock();
        machine.resetAmount();

        if (machine.getStock() <= 0) {
            System.out.println("   ⚠️ Machine is now out of stock.");
            machine.setState(machine.getOutOfStockState());
        } else {
            machine.setState(machine.getIdleState());
        }
        System.out.println("   ✅ Product dispensed successfully!");
    }

    @Override
    public void refund() {
        System.out.println("   ❌ Cannot refund during dispensing.");
    }

    @Override
    public String getStateName() {
        return "DISPENSING (Product being dispensed)";
    }
}