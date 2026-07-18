package com.naviroq.patterns.creational.abstractfactory.cloud.aws;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;

public class RDSDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("[AWS RDS] Connecting to managed relational database");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("[AWS RDS] Executing: " + query);
    }

    @Override
    public void disconnect() {
        System.out.println("[AWS RDS] Closing connection");
    }

    @Override
    public String getDatabaseType() {
        return "AWS RDS Relational Database";
    }
}
