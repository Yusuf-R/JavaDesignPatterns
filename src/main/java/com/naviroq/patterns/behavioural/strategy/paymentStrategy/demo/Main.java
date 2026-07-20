package com.naviroq.patterns.behavioural.strategy.paymentStrategy.demo;

import com.naviroq.patterns.behavioural.strategy.paymentStrategy.PaymentStrategy;
import com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.creditCard.CreditCardStrategy;
import com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.crypto.CryptoStrategy;
import com.naviroq.patterns.behavioural.strategy.paymentStrategy.gateway.paypal.PayPalStrategy;
import com.naviroq.patterns.behavioural.strategy.paymentStrategy.services.CheckoutService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("💳 ====== PAYMENT SYSTEM (Strategy Pattern) ======\n");

        CheckoutService cart = new CheckoutService();

        while (true) {
            System.out.println("\n--- Select Payment Method ---");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.println("3. Cryptocurrency");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            if (choice.equals("4")) {
                System.out.println("👋 Goodbye!");
                break;
            }

            System.out.print("Enter total amount: $");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount.");
                continue;
            }

            PaymentStrategy strategy = null;

            switch (choice) {
                case "1":
                    System.out.print("Card number: ");
                    String cardNumber = scanner.nextLine();
                    System.out.print("Expiry (MM/YY): ");
                    String expiry = scanner.nextLine();
                    System.out.print("CVV: ");
                    String cvv = scanner.nextLine();
                    strategy = new CreditCardStrategy(cardNumber, expiry, cvv);
                    break;

                case "2":
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    strategy = new PayPalStrategy(email);
                    break;

                case "3":
                    System.out.print("Wallet address: ");
                    String wallet = scanner.nextLine();
                    strategy = new CryptoStrategy(wallet);
                    break;

                default:
                    System.out.println("❌ Invalid option.");
                    continue;
            }

            cart.setPaymentStrategy(strategy);
            cart.checkout(amount);
        }

        scanner.close();
    }
}
