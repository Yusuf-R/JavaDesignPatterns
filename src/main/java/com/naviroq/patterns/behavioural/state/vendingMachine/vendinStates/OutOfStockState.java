package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class OutOfStockState implements VendingState {
    private VendingMachine machine;

    public OutOfStockState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("   ❌ Machine is out of stock. Cannot accept coins.");
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("   ❌ Machine is out of stock. No products available.");
    }

    @Override
    public void dispense() {
        System.out.println("   ❌ Machine is out of stock.");
    }

    @Override
    public void refund() {
        System.out.println("   ❌ No coins to refund.");
    }

    @Override
    public String getStateName() {
        return "OUT_OF_STOCK (Machine empty)";
    }
}
