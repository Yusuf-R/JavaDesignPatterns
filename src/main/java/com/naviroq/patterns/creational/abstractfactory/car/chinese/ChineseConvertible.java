package com.naviroq.patterns.creational.abstractfactory.car.chinese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;

public class ChineseConvertible implements Convertible {
    @Override
    public void start() {
        System.out.println("  [Chinese Convertible] Engine hums. Electric roof folding silently.");
    }

    @Override
    public void stop() {
        System.out.println("  [Chinese Convertible] Engine off. Roof fully secured.");
    }

    @Override
    public void steer() {
        System.out.println("  [Chinese Convertible] Steering is responsive. Wind in your hair.");
    }

    @Override
    public void brake() {
        System.out.println("  [Chinese Convertible] Brakes applied. Confident stopping.");
    }
}
