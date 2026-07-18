package com.naviroq.patterns.creational.factory.car.types;

import com.naviroq.patterns.creational.factory.car.common.Car;

public class Sport implements Car {
    @Override
    public void start() {
        System.out.println("  [SPORT] Engine screams! Turbo boost ENABLED. 🏎️💨");
    }

    @Override
    public void stop() {
        System.out.println("  [SPORT] Engine killed. Carbon ceramic brakes cooling down.");
    }

    @Override
    public void steer() {
        System.out.println("  [SPORT] Steering is razor-sharp. Aero downforce activated.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [SPORT] Brakes slammed. 60-0 mph in 2.8 seconds!");
    }
}