package com.naviroq.patterns.creational.factory.car.types;

import com.naviroq.patterns.creational.factory.car.common.Car;

public class Sedan implements Car {
    @Override
    public void start() {
        System.out.println("Start Engine: The Engine has started ...");
    }

    @Override
    public void stop() {
        System.out.println("Stop Engine: The Engine has stopped ...");
    }

    @Override
    public void brake() {
        System.out.println("Brake Pressed: The car has halted!!");
    }

    @Override
    public void steer() {
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");

    }
}
