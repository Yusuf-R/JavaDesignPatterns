package com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.crypto;

import com.naviroq.patterns.behavioural.strategy.paymentStrategy.PaymentStrategy;

public class CryptoStrategy implements PaymentStrategy {
    private final String walletAddress;

    public CryptoStrategy(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("🪙 Paid " + amount + " using Crypto Wallet: " + walletAddress);
        System.out.println("   Blockchain: Bitcoin Network");
        System.out.println("   Confirmation: 1/3 confirmations");
        System.out.println("   ✅ Transaction submitted!");
    }
}
