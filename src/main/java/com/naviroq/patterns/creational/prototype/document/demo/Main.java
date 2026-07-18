// DocumentMain.java
package com.naviroq.patterns.creational.prototype.document.demo;

import com.naviroq.patterns.creational.prototype.document.Document;
import com.naviroq.patterns.creational.prototype.document.registry.DocumentRegistry;

public class Main {
    public static void main(String[] args) {

        DocumentRegistry registry = new DocumentRegistry();
        registry.listTemplates();

        System.out.println("\n=== NDA FOR TECHCORP ===");
        Document techcorpNda = registry.create("nda");
        techcorpNda.setTitle("NDA - TechCorp v1.2");
        techcorpNda.addParty("TechCorp Inc.");
        techcorpNda.addParty("John Smith Consulting");
        techcorpNda.addClause("Additional: AI Training Data Restrictions");
        System.out.println(techcorpNda);

        System.out.println("\n=== NDA FOR MEDSTART ===");
        Document medstartNda = registry.create("nda");
        medstartNda.setTitle("NDA - MedStart Pharma");
        medstartNda.addParty("MedStart Pharma Ltd.");
        medstartNda.addParty("Research Partner Alpha");
        medstartNda.setJurisdiction("Switzerland");  // Different jurisdiction
        System.out.println(medstartNda);

        System.out.println("\n=== EMPLOYMENT FOR SENIOR DEV ===");
        Document seniorDev = registry.create("employment");
        seniorDev.setTitle("Employment - Senior Software Engineer");
        seniorDev.addParty("Naviroq Technologies");
        seniorDev.addParty("Jane Doe");
        seniorDev.addClause("Additional: Remote Work Policy");
        seniorDev.addClause("Additional: Stock Option Vesting Schedule");
        System.out.println(seniorDev);

        System.out.println("\n=== SERVICE FOR CLOUD MIGRATION ===");
        Document cloudService = registry.create("service");
        cloudService.setTitle("MSA - Cloud Migration Project");
        cloudService.addParty("Acme Corp");
        cloudService.addParty("CloudNine Solutions");
        cloudService.setReviewCycleDays(30);  // Shorter review for urgent project
        System.out.println(cloudService);

        // Verify templates are untouched
        System.out.println("\n=== VERIFY TEMPLATE UNCHANGED ===");
        Document originalNda = registry.create("nda");
        System.out.println("Original NDA title: " + originalNda.getTitle());
        System.out.println("Original NDA parties: " + originalNda.getParties());
    }
}