package com.naviroq.patterns.behavioural.strategy.image.eportStrategy;

public class PNGExport implements ExportStrategy {
    @Override
    public void export(String imageName, byte[] imageData) {
        System.out.println("[PNG] Processing " + imageName + " (" + imageData.length + " bytes)");
        System.out.println("[PNG] Applying lossless compression");
        System.out.println("[PNG] Preserving transparency channel");
        System.out.println("[PNG] Saved: " + imageName + ".png");
    }

    @Override
    public String getFormatName() {
        return "PNG (Lossless)";
    }

    @Override
    public String getFileExtension() {
        return "png";
    }
}
