package com.naviroq.patterns.creational.abstractfactory.car.american;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;

public class AmericanSUV implements SUV {
    @Override
    public void start() {
        System.out.println("  [American SUV] Engine roars! 5.7L HEMI activated.");
    }

    @Override
    public void stop() {
        System.out.println("  [American SUV] Engine stops. 4x4 system disengages.");
    }

    @Override
    public void steer() {
        System.out.println("  [American SUV] Steering is heavy. Feels like turning a tank.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [American SUV] Brakes applied. Nose dives dramatically.");
    }
}