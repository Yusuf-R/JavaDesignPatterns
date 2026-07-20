package com.naviroq.patterns.behavioural.strategy.image.eportStrategy;

public class AVIFExport implements ExportStrategy {
    @Override
    public void export(String imageName, byte[] imageData) {
        System.out.println("[AVIF] Processing " + imageName + " (" + imageData.length + " bytes)");
        System.out.println("[AVIF] AV1 video codec-based encoding");
        System.out.println("[AVIF] Advanced HDR and wide color gamut support");
        int estimatedSize = (int) (imageData.length * 0.15);
        System.out.println("[AVIF] Estimated output: ~" + estimatedSize + " bytes (85% smaller)");
        System.out.println("[AVIF] Saved: " + imageName + ".avif");
    }

    @Override
    public String getFormatName() {
        return "AVIF (Next-Gen)";
    }

    @Override
    public String getFileExtension() {
        return "avif";
    }
}
