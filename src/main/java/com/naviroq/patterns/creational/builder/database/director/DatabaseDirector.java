package com.naviroq.patterns.creational.builder.database.director;

import com.naviroq.patterns.creational.builder.database.DatabaseConnection;

public class DatabaseDirector {

    public DatabaseConnection buildLocal(String username, String password) {
        return new DatabaseConnection.Builder()
                .host("localhost")
                .port(5432)
                .username(username)
                .password(password)
                .poolSize(5)
                .timeoutSeconds(60)
                .sslEnabled(false)
                .readReplica(false)
                .build();
    }

    public DatabaseConnection buildStaging(String username, String password) {
        return new DatabaseConnection.Builder()
                .host("staging.db.internal")
                .port(5432)
                .username(username)
                .password(password)
                .poolSize(15)
                .timeoutSeconds(30)
                .sslEnabled(true)
                .readReplica(false)
                .build();
    }

    public DatabaseConnection buildProduction(String username, String password) {
        return new DatabaseConnection.Builder()
                .host("prod.db.internal")
                .port(5432)
                .username(username)
                .password(password)
                .poolSize(50)
                .timeoutSeconds(10)
                .sslEnabled(true)
                .readReplica(true)
                .build();
    }
}