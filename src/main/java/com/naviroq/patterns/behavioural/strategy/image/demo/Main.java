package com.naviroq.patterns.behavioural.strategy.image.demo;

import com.naviroq.patterns.behavioural.strategy.image.ImageEditor;
import com.naviroq.patterns.behavioural.strategy.image.eportStrategy.*;

public class Main {
    public static void main(String[] args) {
        byte[] rawImage = new byte[5_000_000];  // 5MB raw image

        ImageEditor editor = new ImageEditor("vacation_photo", rawImage);

        // Export for web — small, fast
        System.out.println("=== WEB UPLOAD ===");
        editor.setExportStrategy(new JPEGExport(85));
        editor.export();

        // Export for archive — perfect quality
        System.out.println("\n=== ARCHIVE COPY ===");
        editor.setExportStrategy(new PNGExport());
        editor.export();

        // Export for modern browser
        System.out.println("\n=== MODERN WEB ===");
        editor.setExportStrategy(new WEBPExport());
        editor.export();

        // Export for next-gen devices
        System.out.println("\n=== FUTURE-PROOF ===");
        editor.setExportStrategy(new AVIFExport());
        editor.export();

        // Batch export for all platforms
        System.out.println("\n" + "=".repeat(50));
        ImageEditor logo = new ImageEditor("company_logo", new byte[2_000_000]);
        logo.batchExport(new ExportStrategy[] {
                new PNGExport(),
                new JPEGExport(95),
                new WEBPExport(),
                new AVIFExport()
        });
    }
}
