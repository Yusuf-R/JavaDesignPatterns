package com.naviroq.patterns.structural.adapter.logger.demo;

import com.naviroq.patterns.structural.adapter.logger.adapter.EnterpriseLoggerAdapter;
import com.naviroq.patterns.structural.adapter.logger.legacy.LegacyEnterpriseLogger;
import com.naviroq.patterns.structural.adapter.logger.modern.ModernLogger;

public class Main {
    public static void main(String[] args) {
        LegacyEnterpriseLogger legacyLogger = new LegacyEnterpriseLogger();

        // Adapt it to our modern interface
        ModernLogger authLogger = new EnterpriseLoggerAdapter(legacyLogger, "AuthModule");
        ModernLogger paymentLogger = new EnterpriseLoggerAdapter(legacyLogger, "PaymentModule");

        // Our modern app uses clean interface, legacy handles the logging
        LoggerApplication authApp = new LoggerApplication(authLogger);
        LoggerApplication paymentApp = new LoggerApplication(paymentLogger);

        authApp.authenticateUser("alice");
        authApp.authenticateUser("hacker");
        paymentApp.processPayment(99.99);
    }
}
