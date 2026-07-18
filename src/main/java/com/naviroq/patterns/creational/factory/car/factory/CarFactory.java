package com.naviroq.patterns.creational.factory.car.factory;

import com.naviroq.patterns.creational.factory.car.common.Car;
import com.naviroq.patterns.creational.factory.car.types.*;


public class CarFactory {
    public static Car createCar(SelectionTypes type) {
        if (type == null ) {
            throw new IllegalArgumentException("Input type can not be null");
        }
        return switch (type) {
            case CONVERTIBLE -> new Convertible();
            case COUPE -> new Coupe();
            case HATCHBACK -> new Hatchback();
            case JEEP -> new Jeep();
            case SUV -> new SUV();
            case LIMOUSINE -> new Limousine();
            case SEDAN -> new Sedan();
            case SPORT -> new Sport();
            case WAGON -> new Wagon();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
