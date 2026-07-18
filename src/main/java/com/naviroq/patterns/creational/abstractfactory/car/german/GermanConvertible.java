package com.naviroq.patterns.creational.abstractfactory.car.german;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;

public class GermanConvertible implements Convertible {
    @Override
    public void start() { System.out.println("  [German Convertible] Engine hums. Roof folds in 12 seconds."); }
    @Override
    public void stop() { System.out.println("  [German Convertible] Engine off. Wind deflector retracts."); }
    @Override
    public void steer() { System.out.println("  [German Convertible] Steering is responsive. Enjoy the open air."); }
    @Override
    public void brake() { System.out.println("  [German Convertible] Brakes applied. Sport-tuned response."); }
}