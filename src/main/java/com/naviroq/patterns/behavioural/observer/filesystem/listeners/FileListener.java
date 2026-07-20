package com.naviroq.patterns.behavioural.observer.filesystem.listeners;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;

public interface FileListener {
    void onFileChange(FileEvent event);
}
