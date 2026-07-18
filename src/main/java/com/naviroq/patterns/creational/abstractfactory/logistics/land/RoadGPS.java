package com.naviroq.patterns.creational.abstractfactory.logistics.land;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;

public class RoadGPS implements RoutePlanner {
    @Override
    public void navigate() {
        System.out.println("  🗺️  Navigating via ROADS (GPS for highways).");
    }
}
