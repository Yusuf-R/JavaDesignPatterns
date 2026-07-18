package com.naviroq.patterns.creational.abstractfactory.car.french;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;

public class FrenchConvertible implements Convertible {
    @Override
    public void start() { System.out.println("  [French Convertible] Engine purrs. Roof folds with a flourish."); }
    @Override
    public void stop() { System.out.println("  [French Convertible] Engine off. Canvas roof securely latched."); }
    @Override
    public void steer() { System.out.println("  [French Convertible] Steering is easy. Chic and effortless."); }
    @Override
    public void brake() { System.out.println("  [French Convertible] Brakes applied. Smooth as butter."); }
}