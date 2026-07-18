package com.naviroq.patterns.creational.abstractfactory.car.american;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;

public class AmericanConvertible implements Convertible {
    @Override
    public void start() {
        System.out.println("  [Japanese Convertible] V8 fires up. Top-down cruising begins.");
    }

    @Override
    public void stop() {
        System.out.println("  [Japanese Convertible] Engine off. Wind deflector retracts.");
    }

    @Override
    public void steer() {
        System.out.println("  [Japanese Convertible] Steering is light. Enjoy the open road.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [Japanese Convertible] Brakes applied. Smooth and easy.");
    }
}