package com.naviroq.patterns.behavioural.observer.filesystem.listeners;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;

public class LogListener implements FileListener {
    @Override
    public void onFileChange(FileEvent event) {
        System.out.println("   📝 [Log] " + event);
    }
}
