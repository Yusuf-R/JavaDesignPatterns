package com.naviroq.patterns.structural.composite.filesystem.types;

import com.naviroq.patterns.structural.composite.filesystem.FileSystem;

public class File implements FileSystem {
    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }
}
