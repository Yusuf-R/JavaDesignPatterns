package com.naviroq.patterns.structural.adapter.payment.legacy;

public class LegacyPayment {
    public boolean executeTransaction(String accountNumber, double amountInCents, String currencyCode) {
        System.out.println("[LEGACY BANK] Processing " + amountInCents + " cents from account " + accountNumber);
        System.out.println("[LEGACY BANK] Currency: " + currencyCode);
        System.out.println("[LEGACY BANK] Transaction logged in mainframe format");
        return true;
    }
}
