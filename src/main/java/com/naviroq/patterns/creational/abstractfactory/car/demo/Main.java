package com.naviroq.patterns.creational.abstractfactory.car.demo;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.*;
import com.naviroq.patterns.creational.abstractfactory.car.factory.CarFactory;
import com.naviroq.patterns.creational.abstractfactory.car.factory.CarFactoryProvider;
import com.naviroq.patterns.creational.abstractfactory.car.factory.RegionType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=====================================================================");
        System.out.println("          🏭 ABSTRACT FACTORY: GLOBAL CAR FAMILIES 🏭");
        System.out.println("  Each region produces a complete family (Sedan, SUV, Convertible)");
        System.out.println("=====================================================================\n");

        // Test all regions
        RegionType[] regions = {
                RegionType.AMERICAN,
                RegionType.JAPANESE,
                RegionType.GERMAN,
                RegionType.FRENCH,
                RegionType.CHINESE,
        };

        for (RegionType region : regions) {
            System.out.println("--- " + region + " FAMILY ---");
            CarFactory factory = CarFactoryProvider.getFactory(region);

            // Build the complete family
            Sedan sedan = factory.createSedan();
            SUV SUV = factory.createSuv();
            Convertible convertible = factory.createConvertible();

            System.out.println("  🚗 Sedan:");
            sedan.start();
            sedan.steer();
            sedan.brake();
            sedan.stop();

            System.out.println("  🚙 SUV:");
            SUV.start();
            SUV.steer();
            SUV.brake();
            SUV.stop();

            System.out.println("  🚘 Convertible:");
            convertible.start();
            convertible.steer();
            convertible.brake();
            convertible.stop();

            System.out.println("  ✅ " + region + " family completed successfully.");
            System.out.println("  🔒 GUARANTEE: All three cars came from the SAME region.\n");
        }

        // Final summary
        System.out.println("=====================================================================");
        System.out.println("  🎯 ABSTRACT FACTORY DEMO COMPLETE");
        System.out.println("  📌 The provider selected the correct factory based on Region ENUM.");
        System.out.println("  📌 Each factory produced a consistent set of products.");
        System.out.println("  📌 The calling code cannot mix families (e.g., American Sedan + French SUV).");
        System.out.println("=====================================================================");
    }
}