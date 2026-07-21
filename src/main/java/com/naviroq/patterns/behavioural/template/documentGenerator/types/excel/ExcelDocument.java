package com.naviroq.patterns.behavioural.template.documentGenerator.types.excel;

import com.naviroq.patterns.behavioural.template.documentGenerator.DocumentGenerator;

public class ExcelDocument extends DocumentGenerator {
    @Override
    protected void writeFooter() {
        System.out.println("  📝 Writing footer: [Excel Footer - Page 1 of 1]");
    }

    @Override
    protected String getFileExtension() {
        return ".xls";
    }
}
