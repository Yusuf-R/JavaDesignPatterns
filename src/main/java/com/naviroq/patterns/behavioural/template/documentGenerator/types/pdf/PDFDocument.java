package com.naviroq.patterns.behavioural.template.documentGenerator.types.pdf;

import com.naviroq.patterns.behavioural.template.documentGenerator.DocumentGenerator;

public class PDFDocument extends DocumentGenerator {
    @Override
    protected void writeFooter() {
        System.out.println("  📝 Writing footer: [PDF Footer - Page 1 of 1]");
    }

    @Override
    protected String getFileExtension() {
        return ".pdf";
    }
}
