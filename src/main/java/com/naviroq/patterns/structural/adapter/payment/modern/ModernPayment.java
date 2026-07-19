package com.naviroq.patterns.structural.adapter.payment.modern;

public interface ModernPayment {
    void checkout(double amount, String currency);
}
