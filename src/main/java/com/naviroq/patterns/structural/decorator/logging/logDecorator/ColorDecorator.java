package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public class ColorDecorator extends LoggerDecorator {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";

    public ColorDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        // Simple coloring - if it's log output, we color it.
        // In a real system, you'd detect level from message content.
        String colored = CYAN + message + RESET;
        wrapped.log(colored);
    }
}
