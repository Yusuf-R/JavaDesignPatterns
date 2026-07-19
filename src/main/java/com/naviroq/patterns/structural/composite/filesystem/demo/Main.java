package com.naviroq.patterns.structural.composite.filesystem.demo;

import com.naviroq.patterns.structural.composite.filesystem.types.File;
import com.naviroq.patterns.structural.composite.filesystem.types.Folder;

public class Main {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("resume.pdf", 2048);
        File file2 = new File("photo.jpg", 4096);
        File file3 = new File("readme.txt", 128);

        // Create folder 1
        Folder documents = new Folder("Documents");
        documents.add(file1);
        documents.add(file2);

        // Create folder 2 (nested)
        Folder root = new Folder("Root");
        root.add(documents);
        root.add(file3);

        // Print the entire structure
        root.printStructure("");

        // Get total size (without caring about the structure)
        System.out.println("\nTotal size: " + root.getSize() + " bytes");
    }
}
