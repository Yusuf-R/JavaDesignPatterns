package com.naviroq.patterns.behavioral.observer.filesystem.demo;

import com.naviroq.patterns.behavioural.observer.filesystem.FileEvent;
import com.naviroq.patterns.behavioural.observer.filesystem.listeners.BackupListener;
import com.naviroq.patterns.behavioural.observer.filesystem.listeners.IndexListener;
import com.naviroq.patterns.behavioural.observer.filesystem.listeners.LogListener;
import com.naviroq.patterns.behavioural.observer.filesystem.listeners.SecurityListener;
import com.naviroq.patterns.behavioural.observer.filesystem.watchers.FileWatcher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("📂 ====== FILE WATCHER (Observer Pattern) ======\n");

        // 1. Create the watcher (subject)
        FileWatcher watcher = new FileWatcher();

        // 2. Create listeners (observers)
        LogListener logger = new LogListener();
        IndexListener indexer = new IndexListener();
        BackupListener backup = new BackupListener();
        SecurityListener security = new SecurityListener();

        // 3. Attach them
        System.out.println("--- Attaching Listeners ---");
        watcher.attach(logger);
        watcher.attach(indexer);
        watcher.attach(backup);
        watcher.attach(security);
        System.out.println("✅ 4 listeners attached.\n");

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. File CREATED");
            System.out.println("2. File MODIFIED");
            System.out.println("3. File DELETED");
            System.out.println("4. Detach a listener");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            if (choice.equals("5")) {
                System.out.println("👋 Goodbye!");
                break;
            }

            if (choice.equals("4")) {
                System.out.println("Which listener to detach?");
                System.out.println("  1. Logger");
                System.out.println("  2. Indexer");
                System.out.println("  3. Backup");
                System.out.println("  4. Security");
                System.out.print("Choose: ");
                String detachChoice = scanner.nextLine();

                switch (detachChoice) {
                    case "1": watcher.detach(logger); System.out.println("✅ Logger detached."); break;
                    case "2": watcher.detach(indexer); System.out.println("✅ Indexer detached."); break;
                    case "3": watcher.detach(backup); System.out.println("✅ Backup detached."); break;
                    case "4": watcher.detach(security); System.out.println("✅ Security detached."); break;
                    default: System.out.println("❌ Invalid."); break;
                }
                continue;
            }

            System.out.print("File name: ");
            String fileName = scanner.nextLine();

            System.out.print("File size (bytes): ");
            long fileSize;
            try {
                fileSize = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid size. Using 1024.");
                fileSize = 1024;
            }

            FileEvent.ChangeType type;
            switch (choice) {
                case "1": type = FileEvent.ChangeType.CREATED; break;
                case "2": type = FileEvent.ChangeType.MODIFIED; break;
                case "3": type = FileEvent.ChangeType.DELETED; break;
                default:
                    System.out.println("❌ Invalid option.");
                    continue;
            }

            watcher.simulateChange(fileName, type, fileSize);
        }

        scanner.close();
    }
}