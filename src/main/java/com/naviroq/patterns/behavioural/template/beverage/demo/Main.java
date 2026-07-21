package com.naviroq.patterns.behavioural.template.beverage.demo;

import com.naviroq.patterns.behavioural.template.beverage.Beverage;
import com.naviroq.patterns.behavioural.template.beverage.Coffee;
import com.naviroq.patterns.behavioural.template.beverage.BlackCoffee;
import com.naviroq.patterns.behavioural.template.beverage.Tea;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║      BEVERAGE PREPARATION SYSTEM      ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  Menu: tea | coffee | black | quit    ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        while (true) {
            System.out.print("Your order > ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("Closing shop. Goodbye!");
                break;
            }

            Beverage beverage = createBeverage(input);
            if (beverage != null) {
                System.out.println("\n─── Preparing your " + input + " ───");
                beverage.prepare();
            } else {
                System.out.println("  ❌ Sorry, we don't serve '" + input + "'. Try again.\n");
            }
        }

        scanner.close();
    }

    private static Beverage createBeverage(String type) {
        return switch (type) {
            case "tea"    -> new Tea();
            case "coffee" -> new Coffee();
            case "black"  -> new BlackCoffee();
            default       -> null;
        };
    }
}