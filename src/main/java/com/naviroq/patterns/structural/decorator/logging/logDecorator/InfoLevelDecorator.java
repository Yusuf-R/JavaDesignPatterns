package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public class InfoLevelDecorator extends LoggerDecorator {
    public InfoLevelDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        wrapped.log("[INFO] " + message);
    }
}
