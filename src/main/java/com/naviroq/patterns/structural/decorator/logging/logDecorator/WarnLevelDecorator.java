package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public class WarnLevelDecorator extends LoggerDecorator {
    public WarnLevelDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        wrapped.log("[WARN] " + message);
    }
}
