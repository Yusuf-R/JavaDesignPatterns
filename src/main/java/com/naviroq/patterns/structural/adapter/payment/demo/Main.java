package com.naviroq.patterns.structural.adapter.payment.demo;

import com.naviroq.patterns.structural.adapter.payment.adapter.PaymentSystemAdapter;
import com.naviroq.patterns.structural.adapter.payment.legacy.LegacyPayment;
import com.naviroq.patterns.structural.adapter.payment.modern.ModernPayment;

public class Main {
    public static void main(String[] args) {
        LegacyPayment oldPaymentSystem = new LegacyPayment();

        // We adapt it to work with our modern e-commerce platform
        ModernPayment modernPayment = new PaymentSystemAdapter(oldPaymentSystem, "ACC-123456789");
        // Our modern shopping cart only knows the modern interface
        ShoppingCart cart = new ShoppingCart(modernPayment);

        // Shop like it's 2026, but pay through 1980s mainframe
        cart.purchaseItem("Wireless Headphones", 149.99, "USD");
        cart.purchaseItem("Mechanical Keyboard", 89.50, "USD");
        cart.purchaseItem("USB-C Cable", 12.99, "USD");

    }
}
