package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.french.FrenchConvertible;
import com.naviroq.patterns.creational.abstractfactory.car.french.FrenchSUV;
import com.naviroq.patterns.creational.abstractfactory.car.french.FrenchSedan;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class FrenchCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new FrenchSedan();
    }

    @Override
    public SUV createSuv() {
        return new FrenchSUV();
    }

    @Override
    public Convertible createConvertible() {
        return new FrenchConvertible();
    }
}
