package com.naviroq.patterns.structural.bridge.checkout.gateway.card.paystack;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class PayStackGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing transaction via PayStack API for $" + amount + "\n[PayStack] Payment successful\n");
    }
}
