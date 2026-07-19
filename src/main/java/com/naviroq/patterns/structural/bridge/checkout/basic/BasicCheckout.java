package com.naviroq.patterns.structural.bridge.checkout.basic;

import com.naviroq.patterns.structural.bridge.checkout.Checkout;
import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class BasicCheckout extends Checkout {

    // constructor
    public BasicCheckout(PaymentGateway gateway) {
        super(gateway);
    }

    @Override
    public void completeOrder(double amount) {
        gateway.processPayment(amount);
    }
}
