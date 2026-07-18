// Document.java — the prototype
package com.naviroq.patterns.creational.prototype.document;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private String title;
    private String documentType;
    private List<String> clauses;      // Reference type — needs deep copy
    private List<String> parties;      // Reference type — needs deep copy
    private String jurisdiction;
    private boolean requiresNotarization;
    private int reviewCycleDays;

    public Document() {
        this.clauses = new ArrayList<>();
        this.parties = new ArrayList<>();
    }

    // Copy constructor — deep copy
    public Document(Document other) {
        this.title = other.title;
        this.documentType = other.documentType;
        // Deep copy lists — new ArrayList with copied elements
        this.clauses = new ArrayList<>(other.clauses);
        this.parties = new ArrayList<>(other.parties);
        this.jurisdiction = other.jurisdiction;
        this.requiresNotarization = other.requiresNotarization;
        this.reviewCycleDays = other.reviewCycleDays;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public List<String> getClauses() { return clauses; }
    public List<String> getParties() { return parties; }
    public String getJurisdiction() { return jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }
    public boolean isRequiresNotarization() { return requiresNotarization; }
    public void setRequiresNotarization(boolean requiresNotarization) { this.requiresNotarization = requiresNotarization; }
    public int getReviewCycleDays() { return reviewCycleDays; }
    public void setReviewCycleDays(int reviewCycleDays) { this.reviewCycleDays = reviewCycleDays; }

    public void addClause(String clause) { this.clauses.add(clause); }
    public void addParty(String party) { this.parties.add(party); }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", type='" + documentType + '\'' +
                ", jurisdiction='" + jurisdiction + '\'' +
                ", notarization=" + requiresNotarization +
                ", reviewCycle=" + reviewCycleDays + " days" +
                ", \n  clauses=" + clauses +
                ", \n  parties=" + parties +
                '}';
    }
}