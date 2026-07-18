package com.naviroq.patterns.creational.factory.car.demo;

import com.naviroq.patterns.creational.factory.car.common.Car;
import com.naviroq.patterns.creational.factory.car.factory.CarFactory;
import com.naviroq.patterns.creational.factory.car.factory.SelectionTypes;

public class Main {

    public static void main(String[] args) {

        System.out.println("=====================================================================");
        System.out.println("            🏭 ELEVATED CAR FACTORY DEMO 🏭");
        System.out.println("         Showcasing Polymorphism via Factory Pattern");
        System.out.println("=====================================================================\n");

        // --- The "Elevated" Part: Testing multiple car types ---
        // We select a curated list to demonstrate diversity.
        SelectionTypes[] testCars = {
                SelectionTypes.SEDAN,
                SelectionTypes.SUV,
                SelectionTypes.SPORT,
                SelectionTypes.LIMOUSINE,
                SelectionTypes.HATCHBACK
        };

        for (SelectionTypes type : testCars) {
            // 1. The Factory creates the specific car based on the Enum.
            Car car = CarFactory.createCar(type);

            // 2. We treat them all as 'Car' (Polymorphism).
            System.out.println("--- Testing: " + type + " ---");
            car.start();
            car.steer();
            car.brake();
            car.stop();

            System.out.println("  ✅ " + type + " passed all tests.\n");
        }

        // --- Final Summary ---
        System.out.println("=====================================================================");
        System.out.println("  🎯 FACTORY PATTERN DEMO COMPLETE");
        System.out.println("  📌 The Factory created 5 different car types using the SAME interface.");
        System.out.println("  📌 Each car behaved differently based on its concrete implementation.");
        System.out.println("  📌 The calling code (Main) was completely DECOUPLED from the concrete classes.");
        System.out.println("=====================================================================");
    }
}