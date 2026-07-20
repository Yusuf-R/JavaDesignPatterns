package com.naviroq.patterns.behavioural.strategy.image.eportStrategy;

public class WEBPExport implements ExportStrategy {
    @Override
    public void export(String imageName, byte[] imageData) {
        System.out.println("[WebP] Processing " + imageName + " (" + imageData.length + " bytes)");
        System.out.println("[WebP] VP8 encoding with predictive compression");
        System.out.println("[WebP] Supporting both lossy and lossless modes");
        int estimatedSize = (int) (imageData.length * 0.25);
        System.out.println("[WebP] Estimated output: ~" + estimatedSize + " bytes (75% smaller)");
        System.out.println("[WebP] Saved: " + imageName + ".webp");
    }

    @Override
    public String getFormatName() {
        return "WebP (Modern)";
    }

    @Override
    public String getFileExtension() {
        return "webp";
    }
}
