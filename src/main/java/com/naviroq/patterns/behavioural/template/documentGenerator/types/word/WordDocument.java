package com.naviroq.patterns.behavioural.template.documentGenerator.types.word;

import com.naviroq.patterns.behavioural.template.documentGenerator.DocumentGenerator;

public class WordDocument extends DocumentGenerator {
    @Override
    protected void writeFooter() {
        System.out.println("  📝 Writing footer: [WORD Footer - Page 1 of 1]");
    }

    @Override
    protected String getFileExtension() {
        return ".doc";
    }
}
