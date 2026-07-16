package com.naviroq.patterns.creational.singleton;

import com.naviroq.patterns.creational.singleton.eager.AppConfigEager;
import com.naviroq.patterns.creational.singleton.lazy.AppConfigLazy;
import com.naviroq.patterns.creational.singleton.lazydcl.AppConfigLazyDCL;
import com.naviroq.patterns.creational.singleton.lazyfix.AppConfigLazySync;

public class Main {
    public static void main(String[] args) {

        // --- Test Eager Singleton (Thread-safe by default) ---
        System.out.println("=== TESTING EAGER SINGLETON ===");
        AppConfigEager eager1 = AppConfigEager.getInstance();
        AppConfigEager eager2 = AppConfigEager.getInstance();
        System.out.println("Eager1: " + eager1); // Always true
        System.out.println("Eager2: " + eager2); // Always true
        System.out.println("eager1 == eager2? " + "-> " + (eager1 == eager2)); // Always true
        System.out.println();

        // --- Test AppConfigLazy Singleton (Broken in multi-thread) ---
        System.out.println("=== TESTING LAZY SINGLETON (Multi-thread Attack) ===");
        AppConfigLazy[] results = new AppConfigLazy[10]; // an empty array to store type AppConfig objects

        for (int i = 0; i < 10; i++) {
            int index = i;
            Thread t = new Thread(() -> { // anonymous class, useful for quick implementation
                results[index] = AppConfigLazy.getInstance(); // populating the array with object instance of AppConfig
            });
            t.setName("LazyThread-" + i);
            t.start();
        }

        // instead of e, we can use ignore since the block is empty -> {}
        try { Thread.sleep(2000); } catch (InterruptedException ignored) { }


        System.out.println("\n--- AppConfigLazy Results ---");
        boolean lazyBroken = false;
        AppConfigLazy first = results[0];
        for (int i = 0; i < results.length; i++) {
            System.out.println("Thread-" + i + " got: " + results[i]);
            if (results[i] != first) lazyBroken = true;
        }

        if (lazyBroken) {
            System.out.println("❌ LAZY SINGLETON IS BROKEN! Different objects created.");
        } else {
            System.out.println("✅ AppConfigLazy Singleton survived!");
        }

        // --- Test Lazy Singleton (Synchronized - Should Survive) ---
        System.out.println("\n=== TESTING LAZY SYNC SINGLETON (The Cure) ===");
        AppConfigLazySync[] syncResults = new AppConfigLazySync[10];

        for (int i = 0; i < 10; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                syncResults[index] = AppConfigLazySync.getInstance();
            });
            t.setName("SyncThread-" + i);
            t.start();
        }

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        System.out.println("\n--- AppConfigLazySync Results ---");
        boolean syncBroken = false;
        AppConfigLazySync syncFirst = syncResults[0];
        for (int i = 0; i < syncResults.length; i++) {
            System.out.println("SyncThread-" + i + " got: " + syncResults[i]);
            if (syncResults[i] != syncFirst) syncBroken = true;
        }

        if (syncBroken) {
            System.out.println("❌ SYNC LAZY SINGLETON IS STILL BROKEN! (This shouldn't happen)");
        } else {
            System.out.println("✅ SYNC LAZY SINGLETON SURVIVED! All threads got the SAME object.");
        }


        // --- Test Lazy Singleton (Double-Checked Locking - Should Survive) ---
        System.out.println("\n=== TESTING LAZY DCL SINGLETON (Performance Optimized) ===");
        AppConfigLazyDCL[] dclResults = new AppConfigLazyDCL[10];

        for (int i = 0; i < 10; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                dclResults[index] = AppConfigLazyDCL.getInstance();
            });
            t.setName("DCLThread-" + i);
            t.start();
        }

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        System.out.println("\n--- AppConfigLazyDCL Results ---");
        boolean dclBroken = false;
        AppConfigLazyDCL dclFirst = dclResults[0];
        for (int i = 0; i < dclResults.length; i++) {
            System.out.println("DCLThread-" + i + " got: " + dclResults[i]);
            if (dclResults[i] != dclFirst) dclBroken = true;
        }

        if (dclBroken) {
            System.out.println("❌ DCL LAZY SINGLETON IS STILL BROKEN! (This shouldn't happen)");
        } else {
            System.out.println("✅ DCL LAZY SINGLETON SURVIVED! All threads got the SAME object.");
            System.out.println("   💡 Performance win: Only the first thread waited for the lock.");
        }
    }
}
