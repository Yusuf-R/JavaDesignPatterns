package com.naviroq.patterns.behavioural.observer.filesystem.watchers;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;
import com.naviroq.patterns.behavioural.observer.filesystem.listeners.FileListener;

import java.util.ArrayList;
import java.util.List;

public class FileWatcher {
    private List<FileListener> listeners = new ArrayList<>();

    public void attach(FileListener listener) {
        listeners.add(listener);
    }

    public void detach(FileListener listener) {
        listeners.remove(listener);
    }

    public void notifyAll(FileEvent event) {
        for (FileListener listener : listeners) {
            listener.onFileChange(event);
        }
    }

    public void simulateChange(String filename, FileEvent.ChangeType changeType, long size) {
        FileEvent event = new FileEvent(filename, changeType, size);
        System.out.println("\n📁 [Watcher] Detected: " + event);
        notifyAll(event);
    }
}
