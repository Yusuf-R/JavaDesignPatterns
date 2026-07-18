package com.naviroq.patterns.creational.abstractfactory.logistics.demo;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;
import com.naviroq.patterns.creational.abstractfactory.logistics.factory.LogisticsFactory;
import com.naviroq.patterns.creational.abstractfactory.logistics.factory.LogisticsFactoryProvider;
import com.naviroq.patterns.creational.abstractfactory.logistics.factory.LogisticsType;

public class Main {
    public static void main(String[] args) {

        System.out.println("========== ABSTRACT FACTORY DEMO (Transport) ==========\n");

        // --- Test 1: Land Logistics ---
        System.out.println("--- Scenario 1: LAND Logistics (Truck + RoadGPS + RoadManifest) ---");
        LogisticsFactory landFactory = LogisticsFactoryProvider.getFactory(LogisticsType.LAND);

        Transport landVehicle = landFactory.createTransport();
        RoutePlanner landGPS = landFactory.createRoutePlanner();
        Manifest landDoc = landFactory.createManifest();

        landVehicle.deliver();
        landGPS.navigate();
        landDoc.print();

        // --- Test 2: Sea Logistics ---
        System.out.println("\n--- Scenario 2: SEA Logistics (Ship + SeaNavigation + SeaManifest) ---");
        LogisticsFactory seaFactory = LogisticsFactoryProvider.getFactory(LogisticsType.SEA);

        Transport seaVehicle = seaFactory.createTransport();
        RoutePlanner seaGPS = seaFactory.createRoutePlanner();
        Manifest seaDoc = seaFactory.createManifest();

        seaVehicle.deliver();
        seaGPS.navigate();
        seaDoc.print();

        // --- The "Impossible Mix" Proof ---
        System.out.println("\n--- The Compile-Time Guarantee ---");
        System.out.println("Notice how we picked 'LogisticsType.LAND' once.");
        System.out.println("The compiler guarantees that ALL THREE components (Transport, Planner, Manifest)");
        System.out.println("come from the SAME family (LAND).");

       System.out.println("\n✅ The Abstract Factory pattern ENFORCES family consistency at compile-time.");
    }
}
