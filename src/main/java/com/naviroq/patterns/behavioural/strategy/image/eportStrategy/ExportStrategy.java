package com.naviroq.patterns.behavioural.strategy.image.eportStrategy;

public interface ExportStrategy {
    void export(String imageName, byte[] imageData);
    String getFormatName();
    String getFileExtension();
}
