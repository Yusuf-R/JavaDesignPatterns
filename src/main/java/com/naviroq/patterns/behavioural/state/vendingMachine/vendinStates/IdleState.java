package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class IdleState implements VendingState {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("   💰 Coin inserted.");
        machine.addAmount(100); // Assume $1.00
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("   ❌ Please insert a coin first.");
    }

    @Override
    public void dispense() {
        System.out.println("   ❌ Please insert a coin first.");
    }

    @Override
    public void refund() {
        System.out.println("   ❌ No coin to refund.");
    }

    @Override
    public String getStateName() {
        return "IDLE (Waiting for coin)";
    }
}
