package com.naviroq.patterns.structural.adapter.payment.demo;

import com.naviroq.patterns.structural.adapter.payment.modern.ModernPayment;

public class ShoppingCart {
    private final ModernPayment paymentSystem;

    public ShoppingCart(ModernPayment paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public void purchaseItem(String itemName, double price, String currency) {
        System.out.println("\n=== ModernPayment Shopping Cart ===");
        System.out.println("Item: " + itemName);
        System.out.println("Price: " + price + " " + currency);
        System.out.println("Initiating checkout...");

        paymentSystem.checkout(price, currency);

        System.out.println("Purchase complete!");
    }
}
