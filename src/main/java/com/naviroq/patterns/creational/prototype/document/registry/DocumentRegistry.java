// DocumentRegistry.java — the template store
package com.naviroq.patterns.creational.prototype.document.registry;

import com.naviroq.patterns.creational.prototype.document.Document;

import java.util.HashMap;
import java.util.Map;

public class DocumentRegistry {

    private Map<String, Document> templates = new HashMap<>();

    public DocumentRegistry() {
        initializeTemplates();
    }

    private void initializeTemplates() {
        // NDA Template
        Document nda = new Document();
        nda.setTitle("Non-Disclosure Agreement");
        nda.setDocumentType("NDA");
        nda.setJurisdiction("Delaware, USA");
        nda.setRequiresNotarization(false);
        nda.setReviewCycleDays(30);
        nda.addClause("Definition of Confidential Information");
        nda.addClause("Obligations of Receiving Party");
        nda.addClause("Term of Agreement");
        nda.addClause("Return of Information");
        nda.addClause("Remedies");

        // Employment Contract Template
        Document employment = new Document();
        employment.setTitle("Employment Agreement");
        employment.setDocumentType("EMPLOYMENT");
        employment.setJurisdiction("California, USA");
        employment.setRequiresNotarization(false);
        employment.setReviewCycleDays(90);
        employment.addClause("Position and Duties");
        employment.addClause("Compensation and Benefits");
        employment.addClause("Intellectual Property Assignment");
        employment.addClause("Non-Compete");
        employment.addClause("Termination Conditions");
        employment.addClause("Severance");

        // Service Agreement Template
        Document service = new Document();
        service.setTitle("Master Service Agreement");
        service.setDocumentType("SERVICE");
        service.setJurisdiction("New York, USA");
        service.setRequiresNotarization(true);
        service.setReviewCycleDays(60);
        service.addClause("Scope of Services");
        service.addClause("Payment Terms");
        service.addClause("Service Level Agreements");
        service.addClause("Liability and Indemnification");
        service.addClause("Data Protection");
        service.addClause("Governing Law");

        templates.put("nda", nda);
        templates.put("employment", employment);
        templates.put("service", service);
    }

    public Document create(String templateKey) {
        Document template = templates.get(templateKey);
        if (template == null) {
            throw new IllegalArgumentException("Unknown template: " + templateKey);
        }
        return new Document(template);  // Deep copy via copy constructor
    }

    public void listTemplates() {
        System.out.println("Available templates:");
        templates.keySet().forEach(key ->
                System.out.println("  - " + key + ": " + templates.get(key).getTitle())
        );
    }
}