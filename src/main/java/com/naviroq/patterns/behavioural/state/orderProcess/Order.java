package com.naviroq.patterns.behavioural.state.orderProcess;

public class Order {
    private final String orderId;
    private OrderState state;

    public Order(String orderId) {
        this.orderId = orderId;
        this.state = new NewState(); // Initial state
    }

    // Delegate all actions to current state
    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    // Package-private: only states can change state
    void setState(OrderState newState) {
        this.state = newState;
        System.out.println("  → State changed to: " + newState.getName());
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStateName() {
        return state.getName();
    }

    @Override
    public String toString() {
        return String.format("Order[%s | %s]", orderId, state.getName());
    }
}
