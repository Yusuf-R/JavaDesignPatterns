package com.naviroq.patterns.behavioural.observer.filesystem.listeners;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;

public class BackupListener implements FileListener {
    @Override
    public void onFileChange(FileEvent event) {
        if (event.getChangeType() == FileEvent.ChangeType.DELETED) {
            System.out.println("   💾 [Backup] Skipping deleted file: " + event.getFilename());
        } else {
            System.out.println("   💾 [Backup] Backing up " + event.getFilename() + " (" + event.getSize() + " bytes)");
        }
    }
}
