package com.naviroq.patterns.behavioural.observer.filesystem;

public class FileEvent {
    public enum ChangeType { CREATED, MODIFIED, DELETED }

    private final String filename;
    private final ChangeType changeType;
    private final long size;

    public FileEvent(String filename, ChangeType changeType, long size) {
        this.filename = filename;
        this.changeType = changeType;
        this.size = size;
    }

    public String getFilename() { return filename; }
    public ChangeType getChangeType() { return changeType; }
    public long getSize() { return size; }

    @Override
    public String toString() {
        return changeType + " " + filename + " (" + size + " bytes)";
    }
}
