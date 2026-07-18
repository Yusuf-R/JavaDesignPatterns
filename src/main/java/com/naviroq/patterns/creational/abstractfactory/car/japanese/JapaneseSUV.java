package com.naviroq.patterns.creational.abstractfactory.car.japanese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;

public class JapaneseSUV implements SUV {
    @Override
    public void start() {
        System.out.println("  [Japanese SUV] Engine starts quietly. 4WD system ready.");
    }

    @Override
    public void stop() {
        System.out.println("  [Japanese SUV] Engine off. Hill-start assist engaged.");
    }

    @Override
    public void steer() {
        System.out.println("  [Japanese SUV] Steering is confident. Off-road trail ready.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [Japanese SUV] Brakes applied. Traction control is seamless.");
    }
}