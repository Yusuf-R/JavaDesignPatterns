package com.naviroq.patterns.structural.bridge.checkout;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public abstract class Checkout {
    // 💡 THE BRIDGE: Checkout HAS-A Gateway processing tool
    protected PaymentGateway gateway;

    protected Checkout(PaymentGateway gateway) {
        this.gateway = gateway; // lose relationship -- PaymentGateway is Independent of Checkout
    }

    public abstract void completeOrder(double amount);
}
