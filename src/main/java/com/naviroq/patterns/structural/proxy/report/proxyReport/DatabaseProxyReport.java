package com.naviroq.patterns.structural.proxy.report.proxyReport;

import com.naviroq.patterns.structural.proxy.report.DatabaseReport;
import com.naviroq.patterns.structural.proxy.report.Report;

public class DatabaseProxyReport implements Report {
    private DatabaseReport dbReport;
    private final String reportName;


    public DatabaseProxyReport (String reportName) {
        this.reportName = reportName;
    }

    @Override
    public void displayReport() {
        // Create a HAS-A - tightly coupled Constructor to the DatabaseReport
        if (dbReport == null) {
            // you can use this.dbReport too
            System.out.println(">> First time request detected. Bootstrapping real service...");
            dbReport = new DatabaseReport(reportName);
        }
        dbReport.displayReport();
    }
}
