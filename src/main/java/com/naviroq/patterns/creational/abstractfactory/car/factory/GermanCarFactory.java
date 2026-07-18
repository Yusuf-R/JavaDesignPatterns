package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.german.GermanConvertible;
import com.naviroq.patterns.creational.abstractfactory.car.german.GermanSUV;
import com.naviroq.patterns.creational.abstractfactory.car.german.GermanSedan;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class GermanCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new  GermanSedan();
    }

    @Override
    public SUV createSuv() {
        return new  GermanSUV();
    }

    @Override
    public Convertible createConvertible() {
        return new  GermanConvertible();
    }
}
