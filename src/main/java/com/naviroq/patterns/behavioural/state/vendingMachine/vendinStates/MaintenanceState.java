package com.naviroq.patterns.behavioural.state.vendingMachine.vendinStates;

import com.naviroq.patterns.behavioural.state.vendingMachine.VendingMachine;

public class MaintenanceState implements VendingState {
    private final VendingMachine machine;

    public MaintenanceState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("   🔧 Machine is under maintenance. Cannot accept coins.");
    }

    @Override
    public void selectProduct(String product) {
        System.out.println("   🔧 Machine is under maintenance. Cannot dispense products.");
    }

    @Override
    public void dispense() {
        System.out.println("   🔧 Machine is under maintenance. Dispensing disabled.");
    }

    @Override
    public void refund() {
        System.out.println("   🔧 No coins to refund during maintenance.");
    }
    @Override
    public String getStateName() {
        return "Maintenance (System is undergoing maintenance.)";
    }
}
