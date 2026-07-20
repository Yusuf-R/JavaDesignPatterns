package com.naviroq.patterns.structural.proxy.report.demo;

import com.naviroq.patterns.structural.proxy.report.DatabaseReport;
import com.naviroq.patterns.structural.proxy.report.Report;
import com.naviroq.patterns.structural.proxy.report.proxyReport.DatabaseProxyReport;

public class Main {
    public static void main(String[] args) {
        // RealReport();
        OptimizeReportWithProxy();
    }

    public static void RealReport() {
        DatabaseReport HRReport = new DatabaseReport("Employee_Performance_2026");

        System.out.println("--- Dashboard UI Loaded Successfully ---");
        // The user is clicking around, everything is fast...

        System.out.println("\n[User clicks 'View Employee_Performance Report']");
        // 2. The database query fires ONLY now!
        HRReport.displayReport();

        System.out.println("\n[User clicks 'Employee_Performance' again]");
        // 3. Instantaneous! The ec2Instance skips the query because it already cached the real object reference.
       for (int i = 0 ; i <= 100; i ++){
           HRReport.displayReport();
       }
    }

    public static void OptimizeReportWithProxy() {
        Report salesReport = new DatabaseProxyReport("Q2_Sales_Report");
        Report HRReport = new DatabaseProxyReport("Employee_Performance_2026");

        System.out.println("--- Dashboard UI Loaded Successfully ---");
        // The user is clicking around, everything is fast...

        System.out.println("\n[User clicks 'View Sales Report']");
        // 2. The database query fires ONLY now!
        salesReport.displayReport();

        System.out.println("\n[User clicks 'View Sales Report' again]");
        // 3. Instantaneous! The ec2Instance skips the query because it already cached the real object reference.
        for (int i = 0 ; i <= 100; i ++){
            HRReport.displayReport();
        }

    }
}
