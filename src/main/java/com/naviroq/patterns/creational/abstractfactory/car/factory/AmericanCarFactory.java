package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.*;
import com.naviroq.patterns.creational.abstractfactory.car.american.AmericanSedan;
import com.naviroq.patterns.creational.abstractfactory.car.american.AmericanSUV;
import com.naviroq.patterns.creational.abstractfactory.car.american.AmericanConvertible;

public class AmericanCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new AmericanSedan();
    }

    @Override
    public SUV createSuv() {
        return new AmericanSUV();
    }

    @Override
    public Convertible createConvertible() {
        return new AmericanConvertible();
    }
}