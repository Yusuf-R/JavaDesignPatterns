package com.naviroq.patterns.creational.abstractfactory.car.french;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;

public class FrenchSUV implements SUV {
    @Override
    public void start() { System.out.println("  [French SUV] Engine starts. Rear-wheel steering engaged."); }
    @Override
    public void stop() { System.out.println("  [French SUV] Engine off. All-terrain mode saved."); }
    @Override
    public void steer() { System.out.println("  [French SUV] Steering is playful. Soft-roader handling."); }
    @Override
    public void brake() { System.out.println("  [French SUV] Brakes applied. Comfortable deceleration."); }
}