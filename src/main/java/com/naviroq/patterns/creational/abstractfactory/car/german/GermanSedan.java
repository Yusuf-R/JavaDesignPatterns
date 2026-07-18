package com.naviroq.patterns.creational.abstractfactory.car.german;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.*;

public class GermanSedan implements Sedan {
    @Override
    public void start() { System.out.println("  [German Sedan] Engine starts with precision. Autobahn mode ready."); }
    @Override
    public void stop() { System.out.println("  [German Sedan] Engine shuts off. Auto start-stop engaged."); }
    @Override
    public void steer() { System.out.println("  [German Sedan] Steering is surgical. Cornering at speed."); }
    @Override
    public void brake() { System.out.println("  [German Sedan] Brakes applied. Ceramic discs bite hard."); }
}