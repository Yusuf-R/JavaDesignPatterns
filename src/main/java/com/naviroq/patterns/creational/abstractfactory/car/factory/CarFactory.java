package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.*;

public interface CarFactory {
    // this to say ANY type of car factory must make sedan type, SUV and Convertible types
    Sedan createSedan();
    SUV createSuv();
    Convertible createConvertible();
}