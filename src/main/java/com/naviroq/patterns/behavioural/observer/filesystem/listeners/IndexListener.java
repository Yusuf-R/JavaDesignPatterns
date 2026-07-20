package com.naviroq.patterns.behavioural.observer.filesystem.listeners;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;

public class IndexListener implements FileListener {
    private int indexedCount = 0;

    @Override
    public void onFileChange(FileEvent event) {
        if (event.getChangeType() == FileEvent.ChangeType.DELETED) {
            indexedCount--;
        } else {
            indexedCount++;
        }
        System.out.println("   🔍 [Index] Total files: " + indexedCount + " | Last event: " + event.getFilename());
    }
}
