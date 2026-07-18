package com.naviroq.patterns.creational.factory.car.types;

import com.naviroq.patterns.creational.factory.car.common.Car;

public class Limousine implements Car {
    @Override
    public void start() {
        System.out.println("  [LIMOUSINE] Engine purrs silently. Champagne cooler activated. 🥂");
    }

    @Override
    public void stop() {
        System.out.println("  [LIMOUSINE] Glides to a stop. Air suspension levels.");
    }

    @Override
    public void steer() {
        System.out.println("  [LIMOUSINE] Steering is smooth and light. Extended wheelbase handles gracefully.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [LIMOUSINE] Brakes applied gently. Passengers didn't spill a drop.");
    }
}