package com.naviroq.patterns.structural.facade.startCar.demo;

import com.naviroq.patterns.structural.facade.startCar.facade.CarSystem;

public class Main {
    public static void main(String[] args) {
        CarSystem car = new CarSystem();
        car.startCar(); // One call. The Facade handles the rest.
    }
}
