package com.naviroq.patterns.creational.abstractfactory.car.american;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class AmericanSedan implements Sedan {
    @Override
    public void start() {
        System.out.println("  [American Sedan] V8 rumbles to life. 'Murica!");
    }

    @Override
    public void stop() {
        System.out.println("  [American Sedan] Engine shuts off. Fuel gauge drops noticeably.");
    }

    @Override
    public void steer() {
        System.out.println("  [American Sedan] Steering is floaty. Feels like piloting a boat.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [American Sedan] Brakes applied. Stopping distance: 'eventually'.");
    }
}
