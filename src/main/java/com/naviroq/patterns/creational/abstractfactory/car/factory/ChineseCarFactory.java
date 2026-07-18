package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.chinese.ChineseConvertible;
import com.naviroq.patterns.creational.abstractfactory.car.chinese.ChineseSUV;
import com.naviroq.patterns.creational.abstractfactory.car.chinese.ChineseSedan;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.Convertible;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.SUV;
import com.naviroq.patterns.creational.abstractfactory.car.common.types.Sedan;

public class ChineseCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new ChineseSedan();
    }

    @Override
    public SUV createSuv() {
        return new ChineseSUV();
    }

    @Override
    public Convertible createConvertible() {
        return new ChineseConvertible();
    }
}
