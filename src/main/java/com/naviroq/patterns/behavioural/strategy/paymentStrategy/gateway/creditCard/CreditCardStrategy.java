package com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.creditCard;

import com.naviroq.patterns.behavioural.strategy.paymentStrategy.PaymentStrategy;

public class CreditCardStrategy implements PaymentStrategy {
    private final String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCardStrategy(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💳 Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("   Card: " + cardNumber);
        System.out.println("   Expiry: " + expiryDate);
        System.out.println("   CVV: ***");
        System.out.println("   ✅ Transaction approved!");
    }
}
