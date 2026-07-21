package com.naviroq.patterns.behavioural.template.documentGenerator.types.markdown;

import com.naviroq.patterns.behavioural.template.documentGenerator.DocumentGenerator;

public class MarkdownDocument extends DocumentGenerator {
    @Override
    protected void writeFooter() {
        System.out.println("  📝 Writing footer: --- End of Markdown Document ---");
    }

    @Override
    protected String getFileExtension() {
        return ".md";
    }
}
