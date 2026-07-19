package com.naviroq.patterns.structural.adapter.logger.legacy;

public class LegacyEnterpriseLogger {
    public void writeLogEntry(int severityLevel, String timestamp,
                              String moduleName, String messageText) {
        // Writes to mainframe, needs severity codes, timestamps, module names
        System.out.println("[LEGACY][" + timestamp + "][Module:" + moduleName +
                "][Level:" + severityLevel + "] " + messageText);
    }
}
