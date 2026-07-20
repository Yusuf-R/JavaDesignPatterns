package com.naviroq.patterns.structural.proxy.accessControl.demo;

import com.naviroq.patterns.structural.proxy.accessControl.Document;
import com.naviroq.patterns.structural.proxy.accessControl.proxyControl.ProxyViewer;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ADMIN USER ===");
        Document adminDoc = new ProxyViewer("report.pdf", "Q4 Financials", "ADMIN");
        adminDoc.view();
        adminDoc.edit("Updated Q4 numbers");
        adminDoc.delete();

        System.out.println("\n=== EDITOR USER ===");
        Document editorDoc = new ProxyViewer("report.pdf", "Q4 Financials", "EDITOR");
        editorDoc.view();
        editorDoc.edit("Added commentary");
        editorDoc.delete();  // Denied!

        System.out.println("\n=== VIEWER USER ===");
        Document viewerDoc = new ProxyViewer("report.pdf", "Q4 Financials", "VIEWER");
        viewerDoc.view();
        viewerDoc.edit("Hacked!");  // Denied!
        viewerDoc.delete();  // Denied!
    }
}
