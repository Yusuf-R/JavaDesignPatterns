package com.naviroq.patterns.behavioural.strategy.paymentStrategy.services;

import com.naviroq.patterns.behavioural.strategy.paymentStrategy.PaymentStrategy;

public class CheckoutService {
    // 💡 THE STRATEGY KEY: HAS-A reference to the abstraction (DIP in action!)
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double totalAmount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set!");
        }

        // Delegate the actual work to the injected strategy
        paymentStrategy.pay(totalAmount);
    }
}