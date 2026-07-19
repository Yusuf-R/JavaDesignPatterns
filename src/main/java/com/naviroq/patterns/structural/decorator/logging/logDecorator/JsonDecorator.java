package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

public class JsonDecorator extends LoggerDecorator {
    public JsonDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        wrapped.log("{ \"log\": \"" + message + "\" }");
    }
}
