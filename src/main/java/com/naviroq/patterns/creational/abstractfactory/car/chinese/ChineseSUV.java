package com.naviroq.patterns.creational.abstractfactory.car.chinese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;

public class ChineseSUV implements SUV {
    @Override
    public void start() { System.out.println("  [Chinese SUV] Engine starts. Rear-wheel steering engaged."); }
    @Override
    public void stop() { System.out.println("  [Chinese SUV] Engine off. All-terrain mode saved."); }
    @Override
    public void steer() { System.out.println("  [Chinese SUV] Steering is playful. Soft-roader handling."); }
    @Override
    public void brake() { System.out.println("  [Chinese SUV] Brakes applied. Comfortable deceleration."); }
}
