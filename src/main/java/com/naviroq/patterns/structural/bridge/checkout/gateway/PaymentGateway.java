package com.naviroq.patterns.structural.bridge.checkout.gateway;

public interface PaymentGateway {
    void processPayment(double amount);
}