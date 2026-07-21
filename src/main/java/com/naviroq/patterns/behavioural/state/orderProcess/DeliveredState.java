package com.naviroq.patterns.behavioural.state.orderProcess;

public class DeliveredState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("  ❌ Already paid.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("  ❌ Already delivered.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  ❌ Already delivered.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  ❌ Cannot cancel delivered order. Use return process.");
    }

    @Override
    public String getName() { return "DELIVERED"; }
}
