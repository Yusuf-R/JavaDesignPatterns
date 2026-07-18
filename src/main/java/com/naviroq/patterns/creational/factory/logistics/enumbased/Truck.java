package com.naviroq.patterns.creational.factory.logistics.enumbased;
import com.naviroq.patterns.creational.factory.logistics.common.Transport;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("  🚛 Delivering by land in a truck.");
    }
}
