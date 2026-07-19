package com.naviroq.patterns.structural.bridge.checkout.gateway.card.paypal;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class PayPalGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing transaction via PayPal API for $" + amount + "\n[PayPal] Payment successful\n");
    }
}
