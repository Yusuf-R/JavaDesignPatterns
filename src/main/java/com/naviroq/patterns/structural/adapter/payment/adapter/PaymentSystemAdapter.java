package com.naviroq.patterns.structural.adapter.payment.adapter;
import com.naviroq.patterns.structural.adapter.payment.legacy.LegacyPayment;
import com.naviroq.patterns.structural.adapter.payment.modern.ModernPayment;

public class PaymentSystemAdapter implements ModernPayment {
    // will take a modern requirements to fulfill the legacy payment
    private final LegacyPayment legacyPaymentSystem;
    private final String accountNumber;

    public PaymentSystemAdapter(LegacyPayment legacyPaymentSystem, String accNum) {
        this.legacyPaymentSystem = legacyPaymentSystem;
        this.accountNumber = accNum;
    }


    @Override
    public void checkout(double amount, String currency) {
        // ModernPayment:  amount in dollars, simple currency string
        // LegacyPayment:  amount in cents, uppercase currency code, account number
        double amountInCents = amount * 100;
        String currencyCode = currency.toUpperCase();

        System.out.println("[ADAPTER] Converting modern request to legacy format...");
        System.out.println("[ADAPTER] $" + amount + " USD → " + amountInCents + " cents");

        boolean transact = legacyPaymentSystem.executeTransaction(accountNumber, amountInCents, currencyCode);
        if (!transact) {
            throw new RuntimeException("LegacyPayment banking bankTransaction failed");
        }

        System.out.println("[ADAPTER] LegacyPayment bankTransaction completed successfully");
        System.out.println("[ADAPTER] ModernPayment checkout confirmed");
    }
}
