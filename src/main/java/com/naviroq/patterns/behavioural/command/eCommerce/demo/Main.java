package com.naviroq.patterns.behavioural.command.eCommerce.demo;


import com.naviroq.patterns.behavioural.command.eCommerce.OrderContext;
import com.naviroq.patterns.behavioural.command.eCommerce.invoker.OrderProcessor;
import com.naviroq.patterns.behavioural.command.eCommerce.task.createShippingLabelTask.CreateShippingLabel;
import com.naviroq.patterns.behavioural.command.eCommerce.task.processPaymentTask.ProcessPayment;
import com.naviroq.patterns.behavioural.command.eCommerce.task.reserveInventoryTask.ReserveInventory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("📦 ====== E-COMMERCE ORDER PROCESSOR ======\n");

        // Simulate multiple orders
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\n--- NewState Order ---");
            System.out.print("Order ID: ");
            String orderId = scanner.nextLine();
            if (orderId.equalsIgnoreCase("exit")) {
                keepRunning = false;
                continue;
            }

            System.out.print("Customer Email: ");
            String email = scanner.nextLine();
            System.out.print("Item: ");
            String item = scanner.nextLine();
            System.out.print("Quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());
            System.out.print("Amount ($): ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Shipping Address: ");
            String address = scanner.nextLine();

            // Build the context
            OrderContext context = new OrderContext(orderId, email, item, qty, amount, address);

            // Build the tasks (commands)
            OrderProcessor processor = new OrderProcessor();
            processor.addTask(new ReserveInventory(context));
            processor.addTask(new ProcessPayment(context));
            processor.addTask(new CreateShippingLabel(context));

            // Process the order
            boolean success = processor.processOrder();

            // Show final state
            System.out.println("\n📊 Final Status:");
            System.out.println("   Inventory Reserved: " + context.isInventoryReserved());
            System.out.println("   Payment Charged: " + context.isPaymentCharged());
            System.out.println("   Shipping Label: " + context.isShippingLabelCreated());
            System.out.println("   Result: " + (success ? "✅ COMPLETED" : "❌ ROLLED BACK"));

            System.out.println("\nPress Enter to continue, or type 'exit' to quit...");
            String next = scanner.nextLine();
            if (next.equalsIgnoreCase("exit")) {
                keepRunning = false;
            }
        }

        scanner.close();
        System.out.println("👋 Goodbye!");
    }
}