package com.naviroq.patterns.behavioural.observer.filesystem.listeners;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;

public class SecurityListener implements FileListener {
    @Override
    public void onFileChange(FileEvent event) {
        if (event.getFilename().contains("secret")) {
            System.out.println("   🚨 [Security] ALERT: Secret file " + event.getFilename() + " was " + event.getChangeType());
        }
    }
}
