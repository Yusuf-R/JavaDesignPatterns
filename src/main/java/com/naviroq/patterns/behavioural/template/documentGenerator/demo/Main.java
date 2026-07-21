package com.naviroq.patterns.behavioural.template.documentGenerator.demo;

import com.naviroq.patterns.behavioural.template.documentGenerator.DocumentGenerator;
import com.naviroq.patterns.behavioural.template.documentGenerator.types.excel.ExcelDocument;
import com.naviroq.patterns.behavioural.template.documentGenerator.types.html.HtmlDocument;
import com.naviroq.patterns.behavioural.template.documentGenerator.types.markdown.MarkdownDocument;
import com.naviroq.patterns.behavioural.template.documentGenerator.types.pdf.PDFDocument;
import com.naviroq.patterns.behavioural.template.documentGenerator.types.word.WordDocument;

public class Main {
    public static void main(String[] args) {
        System.out.println("📄 ====== DOCUMENT GENERATOR (Template Method) ======\n");

        String title = "Quarterly Report Q3 2024";
        String content = "Sales increased by 15% compared to Q2.\n" +
                "NewState customers: 450\n" +
                "Revenue: $2.5M";

        // Generate HTML
        DocumentGenerator html = new HtmlDocument();
        html.generateDocument(title, content);

        // Generate PDFDocument
        DocumentGenerator pdf = new PDFDocument();
        pdf.generateDocument(title, content);

        // Generate MarkdownDocument
        DocumentGenerator md = new MarkdownDocument();
        md.generateDocument(title, content);

        // Generate Excel
        DocumentGenerator excel = new ExcelDocument();
        excel.generateDocument(title, content);

        // Generate WordDocument
        DocumentGenerator word = new WordDocument();
        pdf.generateDocument(title, content);
    }
}
