package com.naviroq.patterns.behavioural.state.orderProcess;

public class ShippedState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("  ❌ Already paid.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("  ❌ Already shipped.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  🚚 Out for delivery...");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  ❌ Cannot cancel. Order already shipped. Contact support.");
    }

    @Override
    public String getName() { return "SHIPPED"; }
}
