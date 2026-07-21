package com.naviroq.patterns.behavioural.state.orderProcess;

public class CancelledState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("  ❌ Order cancelled. Create new order.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("  ❌ Order cancelled.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  ❌ Order cancelled.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  ❌ Already cancelled.");
    }

    @Override
    public String getName() { return "CANCELLED"; }
}