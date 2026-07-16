package com.naviroq.patterns.creational.singleton.lazydcl;

public class AppConfigLazyDCL {

    // DCL -> Double Check Locking + Volatile
    // THE CRUCIAL FIX: 'volatile' prevents instruction reordering.
    private static volatile AppConfigLazyDCL instance = null;

    private AppConfigLazyDCL() {
        System.out.println("   [DCL Constructor] Starting to build object...");
        try {
            Thread.sleep(50); // Still forcing a delay for the demo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("   [DCL Constructor] Finished building object!");
    }

    public static AppConfigLazyDCL getInstance() {
        // 1st check: No lock, super fast. If it exists, return immediately.
        if (instance == null) {
            // 2nd check: Only enter if first check says null.
            // We lock on the Class object.
            synchronized (AppConfigLazyDCL.class) {
                // Double-check: Another thread might have created it while we waited for the lock.
                if (instance == null) {
                    instance = new AppConfigLazyDCL();
                }
            }
        }
        return instance;
    }
}