package com.naviroq.patterns.creational.abstractfactory.car.french;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class FrenchSedan implements Sedan {
    @Override
    public void start() { System.out.println("  [French Sedan] Engine starts. Hydraulic suspension rises."); }
    @Override
    public void stop() { System.out.println("  [French Sedan] Engine off. 'Eco' light blinks."); }
    @Override
    public void steer() { System.out.println("  [French Sedan] Steering is light. Single-spoke wheel feels retro."); }
    @Override
    public void brake() { System.out.println("  [French Sedan] Brakes applied. Soft, progressive stop."); }
}