package com.naviroq.patterns.behavioural.state.orderProcess;


public class PaidState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("  ❌ Already paid.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("  📦 Packing and shipping...");
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  ❌ Cannot deliver. Order not shipped yet.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  💸 Issuing refund and cancelling...");
        order.setState(new CancelledState());
    }

    @Override
    public String getName() { return "PAID"; }
}
