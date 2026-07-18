package com.naviroq.patterns.creational.abstractfactory.car.factory;


public class CarFactoryProvider {
    public static CarFactory getFactory(RegionType region) {
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }

        return switch (region) {
            case AMERICAN -> new AmericanCarFactory();
            case JAPANESE -> new JapaneseCarFactory();
            case GERMAN -> new GermanCarFactory();
            case FRENCH -> new FrenchCarFactory();
            case CHINESE -> new ChineseCarFactory();
            default -> throw new IllegalStateException("Unexpected value: " + region);

        };
    }
}
