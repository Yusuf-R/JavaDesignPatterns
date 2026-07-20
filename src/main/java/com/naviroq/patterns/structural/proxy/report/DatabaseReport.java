package com.naviroq.patterns.structural.proxy.report;

public class DatabaseReport implements Report {
    private final String reportName;

    public DatabaseReport(String reportName) {
        this.reportName = reportName;
        // The expensive operation happens right at creation
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        System.out.println(" [SQL] Executing heavy query for " + reportName + "...");
        try {
            // 💡 Add a 4-second sleep to simulate real network/DB latency
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(" [SQL] Data fetched successfully!");
    }

    @Override
    public void displayReport() {
        System.out.println("📊 Rendering report data for: " + reportName);
    }
}
