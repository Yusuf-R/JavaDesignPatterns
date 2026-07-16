package com.naviroq.patterns.creational.singleton.eager;

public class AppConfigEager {

    // 1. The Eager Vault (pre-heated by the JVM ClassLoader)
    private static final AppConfigEager INSTANCE = new AppConfigEager();
    /**
     * this is static block declaration , it could also be done this way:-
     *
     * <p> private static final AppConfig INSTANCE; </p>
     * <p>
     * static {
     *     INSTANCE = new AppConfigEager();
     * }
     * </p>
     *
     */

    // 2. The Private Lock
    // private constructor, it prevents the instantiation of an object of this class using new AppConfigEager()
    private AppConfigEager() {
        System.out.println("Eager: Configuration loaded at class-load time.");
        // Simulate a heavy load if you want:
        // regardless of the delay, it always GUARANTEES only one instance
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
    }

    // 3. The Public Gatekeeper
    public static AppConfigEager getInstance() {
        return INSTANCE;
    }
}