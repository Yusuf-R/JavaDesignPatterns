package com.naviroq.patterns.creational.abstractfactory.logistics.sea;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("  🚢 Delivering cargo by SEA (Ship).");
    }
}