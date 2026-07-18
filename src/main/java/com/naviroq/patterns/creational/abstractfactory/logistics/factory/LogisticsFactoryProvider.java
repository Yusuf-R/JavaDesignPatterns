package com.naviroq.patterns.creational.abstractfactory.logistics.factory;

public class LogisticsFactoryProvider {
    public static LogisticsFactory getFactory(LogisticsType type) {
        if (type == null) {
            throw new IllegalArgumentException("LogisticsType cannot be null");
        }

        return switch (type) {
            case LAND -> new LandLogisticsFactory();
            case SEA -> new SeaLogisticsFactory();
        };
    }
}
