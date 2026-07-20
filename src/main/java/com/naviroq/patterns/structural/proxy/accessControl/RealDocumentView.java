package com.naviroq.patterns.structural.proxy.accessControl;

public class RealDocumentView implements Document {
    private final String filename;
    private String content;

    public RealDocumentView(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    @Override
    public void view() {
        System.out.println("[RealDocumentView] Viewing " + filename + ": " + content);
    }

    @Override
    public void edit(String newContent) {
        this.content = newContent;
        System.out.println("[RealDocumentView] Edited " + filename);
    }

    @Override
    public void delete() {
        System.out.println("[RealDocumentView] DELETED " + filename);
    }
}
