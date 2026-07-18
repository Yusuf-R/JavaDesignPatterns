package com.naviroq.patterns.creational.abstractfactory.car.japanese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;

public class JapaneseConvertible implements Convertible {
    @Override
    public void start() {
        System.out.println("  [Japanese Convertible] Engine hums. Electric roof folding silently.");
    }

    @Override
    public void stop() {
        System.out.println("  [Japanese Convertible] Engine off. Roof fully secured.");
    }

    @Override
    public void steer() {
        System.out.println("  [Japanese Convertible] Steering is responsive. Wind in your hair.");
        System.out.println("Steer Wheel RIGHT: Car moves in the RIGHT direction ...");
        System.out.println("Steer Wheel LEFT: Car moves in the LEFT direction ...");
    }

    @Override
    public void brake() {
        System.out.println("  [Japanese Convertible] Brakes applied. Confident stopping.");
    }
}