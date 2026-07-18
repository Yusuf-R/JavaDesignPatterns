package com.naviroq.patterns.creational.abstractfactory.logistics.land;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("  🚛 Delivering cargo by LAND (Truck).");
    }
}
