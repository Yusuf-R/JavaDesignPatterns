package com.naviroq.patterns.structural.facade.startCar.facade;

import com.naviroq.patterns.structural.facade.startCar.subsystem.Battery;
import com.naviroq.patterns.structural.facade.startCar.subsystem.Engine;
import com.naviroq.patterns.structural.facade.startCar.subsystem.FuelInjector;
import com.naviroq.patterns.structural.facade.startCar.subsystem.Starter;

public class CarSystem {
    private final Battery battery = new Battery();
    private final FuelInjector fuelInjector = new FuelInjector();
    private final Starter starter = new Starter();
    private final Engine engine = new Engine();

    public void startCar() {
        battery.checkVoltage();
        fuelInjector.pumpFuel();
        starter.engage();
        engine.ignite();
        System.out.println("✅ Car started successfully.");
    }
}