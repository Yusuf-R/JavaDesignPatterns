package com.naviroq.patterns.structural.bridge.checkout.standard;

import com.naviroq.patterns.structural.bridge.checkout.Checkout;
import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class StandardCheckout extends Checkout {
    // constructor
    public StandardCheckout(PaymentGateway gateway) {
        super(gateway);
    }

    @Override
    public void completeOrder(double amount) {
        gateway.processPayment(amount);

    }
}
