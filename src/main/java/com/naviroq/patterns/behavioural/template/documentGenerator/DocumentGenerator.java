package com.naviroq.patterns.behavioural.template.documentGenerator;

public abstract class DocumentGenerator {
    // The Template Method - FINAL so subclasses can't change the algorithm
    public final void generateDocument(String title, String content) {
        System.out.println("\n📄 ====== GENERATING DOCUMENT ======");
        System.out.println("Title: " + title);

        openDocument();
        writeHeader(title);
        writeContent(content);
        writeFooter();
        closeDocument();

        System.out.println("✅ Document generated: " + getFileExtension());
        System.out.println("========================================\n");
    }

    // --- Steps that are the same for all subclasses ---
    private void openDocument() {
        System.out.println("  📂 Opening document...");
    }

    private void writeHeader(String title) {
        System.out.println("  📝 Writing header: " + title);
    }

    private void writeContent(String content) {
        System.out.println("  📝 Writing content: " + content);
    }

    private void closeDocument() {
        System.out.println("  💾 Closing and saving document...");
    }

    // --- Steps that VARY (abstract) ---
    protected abstract void writeFooter();

    protected abstract String getFileExtension();

    // Optional hook method (subclasses can override if they need it)
    protected boolean isDebugMode() {
        return false;
    }
}
