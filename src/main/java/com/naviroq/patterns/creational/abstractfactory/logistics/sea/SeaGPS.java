package com.naviroq.patterns.creational.abstractfactory.logistics.sea;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;

public class SeaGPS implements RoutePlanner {
    @Override
    public void navigate() {
        System.out.println("  🧭 Navigating via SEA (Nautical charts & buoys).");
    }
}
