package com.naviroq.patterns.creational.builder.database.demo;

import com.naviroq.patterns.creational.builder.database.DatabaseConnection;
import com.naviroq.patterns.creational.builder.database.director.DatabaseDirector;

public class Main {
    public static void main(String[] args) {

        DatabaseDirector director = new DatabaseDirector();

        System.out.println("=== LOCAL ===");
        DatabaseConnection local = director.buildLocal("dev_user", "dev_pass");
        System.out.println(local);

        System.out.println("\n=== STAGING ===");
        DatabaseConnection staging = director.buildStaging("staging_user", "staging_pass");
        System.out.println(staging);

        System.out.println("\n=== PRODUCTION ===");
        DatabaseConnection prod = director.buildProduction("prod_user", "prod_pass");
        System.out.println(prod);

        System.out.println("\n=== CUSTOM (no director) ===");
        DatabaseConnection custom = new DatabaseConnection.Builder()
                .host("legacy.db.corp")
                .port(3306)
                .username("legacy")
                .password("legacy_pass")
                .poolSize(3)
                .timeoutSeconds(120)
                .sslEnabled(false)
                .build();
        System.out.println(custom);
    }
}

