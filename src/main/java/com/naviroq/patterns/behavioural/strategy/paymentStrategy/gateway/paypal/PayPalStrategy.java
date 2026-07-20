package com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.paypal;

import com.naviroq.patterns.behavioural.strategy.paymentStrategy.PaymentStrategy;

public class PayPalStrategy implements PaymentStrategy {
    private final String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💰 Paid $" + amount + " using PayPal account: " + email);
        System.out.println("   ✅ Payment successful!");
    }
}


