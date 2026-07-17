package com.naviroq.patterns.creational.singleton.lazy;

public class AppConfigLazy {

    // 1. The Lazy Vault (starts empty)
    private static AppConfigLazy instance = null;

    // 2. The Private Lock
    private AppConfigLazy() {
        // Defensive check against Reflection
        if (instance != null) {
            throw new RuntimeException("Reflection attack detected! Singleton instance already exists.");
        }
        System.out.println("   [Lazy Constructor] Starting to build object...");
        try {
            Thread.sleep(50); // Force threads to collide
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("   [Lazy Constructor] Finished building object!");
    }

    // 3. The Public Gatekeeper with the Null Check
    public static AppConfigLazy getInstance() {
        System.out.println(Thread.currentThread().getName() + " entered Lazy getInstance()");

        if (instance == null) {
            System.out.println(Thread.currentThread().getName() + " saw instance is NULL. Creating...");
            instance = new AppConfigLazy();
        } else {
            System.out.println(Thread.currentThread().getName() + " saw instance is NOT null. Returning.");
        }

        return instance;
    }

    protected Object readResolve() {
        // Tell JVM: "Throw away the deserialized copy, return the REAL Singleton instead."
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Protection against Clone attack -- Protects the SINGLETON RULE
        throw new CloneNotSupportedException("Cannot clone a Singleton!");
    }
}
