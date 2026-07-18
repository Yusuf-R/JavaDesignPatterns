package com.naviroq.patterns.creational.abstractfactory.logistics.land;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;

public class RoadManifest implements Manifest {
    @Override
    public void print() {
        System.out.println("  📄 Printing ROAD manifest (Delivery Note for Truck).");
    }
}
