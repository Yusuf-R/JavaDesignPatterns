package com.naviroq.patterns.structural.composite.filesystem;

public interface FileSystem {
    long getSize();
    String getName();
    void printStructure(String indent);
}