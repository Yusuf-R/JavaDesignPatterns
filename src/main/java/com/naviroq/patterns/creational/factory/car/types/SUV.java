package com.naviroq.patterns.creational.factory.car.types;

import com.naviroq.patterns.creational.factory.car.common.Car;

public class SUV implements Car {
    @Override
    public void start() {
        System.out.println("  [SUV] Engine roars to life! 4x4 mode ACTIVATED. 🏔️");
    }

    @Override
    public void stop() {
        System.out.println("  [SUV] Engine stopped. Hill-hold assist engaged.");
    }

    @Override
    public void steer() {
        System.out.println("  [SUV] Steering wheel turns. Off-road tires grip the dirt.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [SUV] Brakes applied. ABS and Traction control kick in.");
    }
}