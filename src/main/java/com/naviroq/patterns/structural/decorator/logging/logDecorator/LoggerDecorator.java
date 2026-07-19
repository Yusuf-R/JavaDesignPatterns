package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public abstract class LoggerDecorator implements Logger {
    protected Logger wrapped;

    public LoggerDecorator(Logger wrapped) {
        this.wrapped = wrapped;
    }
}
