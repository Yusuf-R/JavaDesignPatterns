package com.naviroq.patterns.structural.decorator.logging.demo;

import com.naviroq.patterns.structural.decorator.logging.logDecorator.*;
import com.naviroq.patterns.structural.decorator.logging.logger.ConsoleLogger;
import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("📋 ====== LOGGING STUDIO (Decorator Pattern) ======\n");
        System.out.println("This logger wraps base console output with decorators.");
        System.out.println("Each decorator adds a new behavior to the logger.\n");

        Logger currentLogger = new ConsoleLogger();

        while (true) {
            System.out.println("--- Current Logger Output ---");
            System.out.println("(No log written yet. Send a test message to see output.)");
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Timestamp");
            System.out.println("2. Add INFO Level");
            System.out.println("3. Add WARN Level");
            System.out.println("4. Add ERROR Level");
            System.out.println("5. Add JSON Format");
            System.out.println("6. Add Color Coding");
            System.out.println("7. Send Test Log");
            System.out.println("8. Reset (start fresh)");
            System.out.println("9. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    currentLogger = new TimestampDecorator(currentLogger);
                    System.out.println("✅ Added Timestamp.");
                    break;
                case "2":
                    currentLogger = new InfoLevelDecorator(currentLogger);
                    System.out.println("✅ Added INFO Level.");
                    break;
                case "3":
                    currentLogger = new WarnLevelDecorator(currentLogger);
                    System.out.println("✅ Added WARN Level.");
                    break;
                case "4":
                    currentLogger = new ErrorLevelDecorator(currentLogger);
                    System.out.println("✅ Added ERROR Level.");
                    break;
                case "5":
                    currentLogger = new JsonDecorator(currentLogger);
                    System.out.println("✅ Added JSON Format.");
                    break;
                case "6":
                    currentLogger = new ColorDecorator(currentLogger);
                    System.out.println("✅ Added Color Coding.");
                    break;
                case "7":
                    System.out.print("Enter your log message: ");
                    String message = scanner.nextLine();
                    System.out.println("\n📄 OUTPUT:");
                    currentLogger.log(message);
                    System.out.println();
                    break;
                case "8":
                    currentLogger = new ConsoleLogger();
                    System.out.println("✅ Reset. Logger is back to plain console output.");
                    break;
                case "9":
                    System.out.println("👋 Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
