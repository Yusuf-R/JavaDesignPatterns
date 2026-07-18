package com.naviroq.patterns.creational.abstractfactory.car.factory;

import com.naviroq.patterns.creational.abstractfactory.car.common.types.*;
import com.naviroq.patterns.creational.abstractfactory.car.japanese.JapaneseSedan;
import com.naviroq.patterns.creational.abstractfactory.car.japanese.JapaneseSUV;
import com.naviroq.patterns.creational.abstractfactory.car.japanese.JapaneseConvertible;

public class JapaneseCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new JapaneseSedan();
    }

    @Override
    public SUV createSuv() {
        return new JapaneseSUV();
    }

    @Override
    public Convertible createConvertible() {
        return new JapaneseConvertible();
    }
}