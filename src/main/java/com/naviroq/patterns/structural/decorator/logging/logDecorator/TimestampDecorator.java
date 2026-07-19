package com.naviroq.patterns.structural.decorator.logging.logDecorator;

import com.naviroq.patterns.structural.decorator.logging.logger.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampDecorator extends LoggerDecorator {
    public TimestampDecorator(Logger wrapped) {
        super(wrapped);
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        wrapped.log("[" + timestamp + "] " + message);
    }
}
