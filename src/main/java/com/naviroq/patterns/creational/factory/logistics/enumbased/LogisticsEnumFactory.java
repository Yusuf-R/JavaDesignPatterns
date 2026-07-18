package com.naviroq.patterns.creational.factory.logistics.enumbased;
import com.naviroq.patterns.creational.factory.logistics.common.Transport;

public class LogisticsEnumFactory {
    public static Transport createTransport(TransportType mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Transport mode cannot be null");
        }

        return switch (mode) {
            case TRUCK -> new Truck();
            case SHIP -> new Ship();
            // No default needed because enum covers all cases.
            // If we add a new enum value later, Java will give a compile-time warning here!
        };
    }
}
