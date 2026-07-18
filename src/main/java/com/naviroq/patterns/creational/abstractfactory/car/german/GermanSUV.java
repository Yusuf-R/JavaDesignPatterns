package com.naviroq.patterns.creational.abstractfactory.car.german;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;

public class GermanSUV implements SUV {
    @Override
    public void start() { System.out.println("  [German SUV] Twin-turbo V8 roars. Air suspension adjusts."); }
    @Override
    public void stop() { System.out.println("  [German SUV] Engine off. Hill-descent control active."); }
    @Override
    public void steer() { System.out.println("  [German SUV] Steering is precise. Rear-axle steering kicks in."); }
    @Override
    public void brake() { System.out.println("  [German SUV] Brakes applied. Off-road ABS calibrates."); }
}