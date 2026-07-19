package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public class ErrorLevelDecorator extends LoggerDecorator {
    public ErrorLevelDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        wrapped.log("[ERROR] " + message);
    }
}