package com.naviroq.patterns.creational.factory.logistics.stringbased;
import com.naviroq.patterns.creational.factory.logistics.common.Transport;

public class Ship implements Transport {
    @Override
    public void deliver () {
        System.out.println("  🚢 Delivering by sea on a ship.");
    }
}
