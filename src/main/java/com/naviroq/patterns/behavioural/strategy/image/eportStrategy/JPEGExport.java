package com.naviroq.patterns.behavioural.strategy.image.eportStrategy;

public class JPEGExport implements ExportStrategy {
    private final int quality;

    public JPEGExport(int quality) {
        this.quality = quality;
    }

    @Override
    public void export(String imageName, byte[] imageData) {
        System.out.println("[JPEG] Processing " + imageName + " (" + imageData.length + " bytes)");
        System.out.println("[JPEG] Applying DCT compression at " + quality + "% quality");
        int estimatedSize = (int) (imageData.length * (quality / 100.0) * 0.3);
        System.out.println("[JPEG] Estimated output: ~" + estimatedSize + " bytes");
        System.out.println("[JPEG] Discarding transparency (white background)");
        System.out.println("[JPEG] Saved: " + imageName + ".jpg");
    }

    @Override
    public String getFormatName() {
        return "JPEG (Quality " + quality + "%)";
    }

    @Override
    public String getFileExtension() {
        return "jpg";
    }
}
