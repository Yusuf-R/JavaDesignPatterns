package com.naviroq.patterns.structural.decorator.coffeshop.demo;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;
import com.naviroq.patterns.structural.decorator.coffeshop.coffe.SimpleCoffee;
import com.naviroq.patterns.structural.decorator.coffeshop.extras.CaramelDecorator;
import com.naviroq.patterns.structural.decorator.coffeshop.extras.MilkDecorator;
import com.naviroq.patterns.structural.decorator.coffeshop.extras.SugarDecorator;
import com.naviroq.patterns.structural.decorator.coffeshop.extras.WhipDecorator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("     WELCOME TO THE DECORATOR CAFE      ");
        System.out.println("========================================");

        // Start with the pure, baseline object
        Coffee myCoffee = new SimpleCoffee();

        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- Current Order State ---");
            System.out.println("Recipe: " + myCoffee.getDescription());
            System.out.printf("Running Total: $%.2f\n", myCoffee.getCost());
            System.out.println("---------------------------");

            System.out.println("Customize your drink by adding ingredients:");
            System.out.println("1. Add Milk           (+$0.50)");
            System.out.println("2. Add Sugar          (+$0.20)");
            System.out.println("3. Add Caramel Shot   (+$0.90)");
            System.out.println("4. Add Whipped Cream  (+$0.70)");
            System.out.println("5. Finish Order & Checkout");
            System.out.print("Select an option (1-5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Wrap the existing coffee object in a Milk layer
                    myCoffee = new MilkDecorator(myCoffee);
                    System.out.println(">> Added Milk!");
                    break;
                case 2:
                    // Wrap the current state in a Sugar layer
                    myCoffee = new SugarDecorator(myCoffee);
                    System.out.println(">> Added Sugar!");
                    break;
                case 3:
                    // Wrap the current state in a Caramel layer
                    myCoffee = new CaramelDecorator(myCoffee);
                    System.out.println(">> Added a shot of Sweet Caramel!");
                    break;
                case 4:
                    // Wrap the current state in a Whip layer
                    myCoffee = new WhipDecorator(myCoffee);
                    System.out.println(">> Added Whipped Cream!");
                    break;
                case 5:
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose between 1 and 5.");
            }
        }

        // Final receipt processing
        System.out.println("\n========================================");
        System.out.println("             FINAL RECEIPT              ");
        System.out.println("========================================");
        System.out.println("Items Breakdown:");
        System.out.println(" • " + myCoffee.getDescription());
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL AMOUNT DUE: $%.2f\n", myCoffee.getCost());
        System.out.println("========================================");
        System.out.println("Thank you for your business! Enjoy your drink.");

        scanner.close();
    }
}
