package com.naviroq.patterns.creational.abstractfactory.car.japanese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class JapaneseSedan implements Sedan {
    @Override
    public void start() {
        System.out.println("  [Japanese Sedan] Engine purrs silently. Hybrid system engaged.");
    }

    @Override
    public void stop() {
        System.out.println("  [Japanese Sedan] Engine off. Regenerative braking stored energy.");
    }

    @Override
    public void steer() {
        System.out.println("  [Japanese Sedan] Steering is precise and light. Parking is effortless.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [Japanese Sedan] Brakes applied. Smooth deceleration.");
    }
}