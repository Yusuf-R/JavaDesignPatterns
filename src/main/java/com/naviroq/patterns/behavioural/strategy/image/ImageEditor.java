package com.naviroq.patterns.behavioural.strategy.image;

import com.naviroq.patterns.behavioural.strategy.image.eportStrategy.ExportStrategy;

public class ImageEditor {
    private ExportStrategy exportStrategy;
    private final String currentImage;
    private final byte[] imageData;

    public ImageEditor(String imageName, byte[] imageData) {
        this.currentImage = imageName;
        this.imageData = imageData;
    }

    public void setExportStrategy(ExportStrategy strategy) {
        this.exportStrategy = strategy;
        System.out.println("\n[Export format: " + strategy.getFormatName() + "]");
    }

    public void export() {
        if (exportStrategy == null) {
            System.out.println("No export format selected!");
            return;
        }
        exportStrategy.export(currentImage, imageData);
    }

    public void batchExport(ExportStrategy[] strategies) {
        System.out.println("\n=== BATCH EXPORT: " + currentImage + " ===");
        for (ExportStrategy strategy : strategies) {
            setExportStrategy(strategy);
            export();
        }
    }
}
