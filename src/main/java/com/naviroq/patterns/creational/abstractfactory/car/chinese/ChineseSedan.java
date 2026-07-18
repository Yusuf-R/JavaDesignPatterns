package com.naviroq.patterns.creational.abstractfactory.car.chinese;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class ChineseSedan implements Sedan {
    @Override
    public void start() { System.out.println("  [Chinese Sedan] Engine starts with precision. Autobahn mode ready."); }
    @Override
    public void stop() { System.out.println("  [Chinese Sedan] Engine shuts off. Auto start-stop engaged."); }
    @Override
    public void steer() { System.out.println("  [Chinese Sedan] Steering is surgical. Cornering at speed."); }
    @Override
    public void brake() { System.out.println("  [Chinese Sedan] Brakes applied. Ceramic discs bite hard."); }
}