package com.naviroq.patterns.behavioural.state.orderProcess;

public class NewState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("  💳 Processing payment...");
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {
        System.out.println("  ❌ Cannot ship. Order not paid yet.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  ❌ Cannot deliver. Order not shipped yet.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  🗑️ Order cancelled.");
        order.setState(new CancelledState());
    }

    @Override
    public String getName() { return "NEW"; }
}
