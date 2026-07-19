package com.naviroq.patterns.structural.adapter.logger.adapter;

import com.naviroq.patterns.structural.adapter.logger.legacy.LegacyEnterpriseLogger;
import com.naviroq.patterns.structural.adapter.logger.modern.ModernLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnterpriseLoggerAdapter implements ModernLogger {
    LegacyEnterpriseLogger legacyEnterpriseLogger;
    int timeStamp;
    String moduleName;

    public EnterpriseLoggerAdapter (LegacyEnterpriseLogger legacyLogger, String moduleName) {
        this.moduleName = moduleName;
        this.legacyEnterpriseLogger = legacyLogger;
    }

    @Override
    public void logInfo(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        // Severity 1 = INFO in legacy system
        legacyEnterpriseLogger.writeLogEntry(1, timestamp, moduleName, message);
    };

    @Override
    public void logError(String message, Exception e) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String fullMessage = message + " | Exception: " + e.getMessage();
        // Severity 3 = ERROR in legacy system
        legacyEnterpriseLogger.writeLogEntry(3, timestamp, moduleName, fullMessage);
    };
}
