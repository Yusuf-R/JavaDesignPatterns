package com.naviroq.patterns.creational.factory.logistics.demo;
import com.naviroq.patterns.creational.factory.logistics.stringbased.LogisticsStringFactory;
import com.naviroq.patterns.creational.factory.logistics.enumbased.LogisticsEnumFactory;
import com.naviroq.patterns.creational.factory.logistics.enumbased.TransportType;
import com.naviroq.patterns.creational.factory.logistics.common.Transport;

public class FactoryDemo {
    public static void main(String[] args) {
        System.out.println("========== FACTORY - Creational Pattern DEMO ==========\n");

        // ----- PART 1: STRING-BASED FACTORY -----
        System.out.println("--- String-Based Factory ---");

        // Valid usage - works perfectly
        System.out.print("  Creating 'truck': ");
        Transport truck1 = LogisticsStringFactory.createTransport("truck");
        truck1.deliver();

        System.out.print("  Creating 'ship': ");
        Transport ship1 = LogisticsStringFactory.createTransport("ship");
        ship1.deliver();

        // The hidden danger - typo that compiles but crashes at runtime!
        System.out.print("\n  [DANGER] Creating 'trck' (typo): ");
        try {
            Transport invalid = LogisticsStringFactory.createTransport("trck");
            invalid.deliver(); // This line never runs
        } catch (IllegalArgumentException e) {
            System.out.println("❌ CRASHED: " + e.getMessage());
        }

        System.out.println("\n  ⚠️  String Factory: Typo compiles fine, but crashes at runtime.");
        System.out.println("  ⚠️  The compiler couldn't help us catch the mistake.\n");

        // ----- PART 2: ENUM-BASED FACTORY -----
        System.out.println("--- Enum-Based Factory ---");

        // Valid usage - works perfectly
        System.out.print("  Creating TRUCK: ");
        Transport truck2 = LogisticsEnumFactory.createTransport(TransportType.TRUCK);
        truck2.deliver();

        System.out.print("  Creating SHIP: ");
        Transport ship2 = LogisticsEnumFactory.createTransport(TransportType.SHIP);
        ship2.deliver();

        System.out.println("\n  ✅ Enum Factory: Invalid types are caught at COMPILE-TIME.");
        System.out.println("  ✅ The IDE will show a red underline if you make a typo.\n");

        // ----- SUMMARY -----
        System.out.println("========== KEY TAKEAWAY ==========");
        System.out.println("Both factories behave the same at runtime.");
        System.out.println("But Enum Factory catches developer mistakes EARLY (compile-time).");
        System.out.println("String Factory hides typos until PRODUCTION (runtime crash).");
        System.out.println("\n✅ For production code, prefer the Enum-based approach.");
    }
}
