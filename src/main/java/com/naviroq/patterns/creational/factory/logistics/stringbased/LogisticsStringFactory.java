package com.naviroq.patterns.creational.factory.logistics.stringbased;
import com.naviroq.patterns.creational.factory.logistics.common.Transport;

public class LogisticsStringFactory {

    public static Transport createTransport(String mode) {
        if (mode == null ){
            throw new IllegalArgumentException("Transport mode cannot be null");
        }
        return switch (mode.toLowerCase()) {
            case "truck" -> new Truck();
            case "ship" -> new Ship();
            default -> throw new IllegalArgumentException("Unknown transport type: " + mode);
        };
    }
}
