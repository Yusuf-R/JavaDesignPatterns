package com.naviroq.patterns.structural.composite.filesystem.types;

import com.naviroq.patterns.structural.composite.filesystem.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystem {
    private final String name;
    private final List <FileSystem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystem item) {
        children.add(item);
    }

    public void remove(FileSystem item) {
        children.remove(item);
    }

    @Override
    public long getSize() {
        long total = 0;
        for (FileSystem child : children) {
            total += child.getSize();
        }
        return total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "📁 " + name + " (Total: " + getSize() + " bytes)");
        for (FileSystem child : children) {
            child.printStructure(indent + "  ");
        }
    }
}
