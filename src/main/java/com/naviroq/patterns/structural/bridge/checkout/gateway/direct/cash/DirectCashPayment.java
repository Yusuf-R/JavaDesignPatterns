package com.naviroq.patterns.structural.bridge.checkout.gateway.direct.cash;

import com.naviroq.patterns.structural.bridge.checkout.gateway.PaymentGateway;

public class DirectCashPayment implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing physical cash drop of $" + amount + "\nCash payment successful!\n");
    }
}
