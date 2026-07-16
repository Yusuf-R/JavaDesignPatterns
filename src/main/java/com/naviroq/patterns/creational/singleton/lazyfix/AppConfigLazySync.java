package com.naviroq.patterns.creational.singleton.lazyfix;

public class AppConfigLazySync {

    // 1. The Lazy Vault (starts empty)
    private static AppConfigLazySync instance = null;

    // 2. The Private Lock
    private AppConfigLazySync() {
        System.out.println("   [Sync Constructor] Starting to build object...");
        try {
            Thread.sleep(50); // Force threads to collide
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("   [Sync Constructor] Finished building object!");
    }

    // 3. The FIXED Public Gatekeeper
    // The 'synchronized' keyword forces threads to line up single-file.
    public static synchronized AppConfigLazySync getInstance() {
        System.out.println(Thread.currentThread().getName() + " entered Sync getInstance()");

        if (instance == null) {
            System.out.println(Thread.currentThread().getName() + " saw instance is NULL. Creating...");
            instance = new AppConfigLazySync();
        } else {
            System.out.println(Thread.currentThread().getName() + " saw instance is NOT null. Returning.");
        }

        return instance;
    }
}