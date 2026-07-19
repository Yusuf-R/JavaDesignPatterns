package com.naviroq.patterns.structural.adapter.logger.demo;

import com.naviroq.patterns.structural.adapter.logger.modern.ModernLogger;

public class LoggerApplication {
    private final ModernLogger logger;

    public LoggerApplication(ModernLogger logger) {
        this.logger = logger;
    }

    public void authenticateUser(String username) {
        logger.logInfo("Authentication attempt for user: " + username);

        try {
            // Simulate auth logic
            if (username.equals("hacker")) {
                throw new SecurityException("Invalid credentials");
            }
            logger.logInfo("User " + username + " authenticated successfully");
        } catch (Exception e) {
            logger.logError("Authentication failed for " + username, e);
        }
    }

    public void processPayment(double amount) {
        logger.logInfo("Processing payment of $" + amount);
        // ... payment logic
    }
}
