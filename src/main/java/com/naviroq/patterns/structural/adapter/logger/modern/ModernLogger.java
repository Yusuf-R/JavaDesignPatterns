package com.naviroq.patterns.structural.adapter.logger.modern;

public interface ModernLogger {
    void logInfo(String message);
    void logError(String message, Exception e);
}
