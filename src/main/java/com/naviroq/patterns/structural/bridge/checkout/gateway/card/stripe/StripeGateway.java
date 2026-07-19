package com.naviroq.patterns.structural.bridge.checkout.gateway.card.stripe;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class StripeGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Firing digital webhooks via Stripe API for $" + amount  + "\n[Stipe] Payment successful\n");
    }
}
