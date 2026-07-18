package com.naviroq.patterns.creational.abstractfactory.cloud.azure;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;

public class CosmosDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("[Azure Cosmos] Connecting to globally distributed database");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("[Azure Cosmos] Executing: " + query);
    }

    @Override
    public void disconnect() {
        System.out.println("[Azure Cosmos] Closing connection");
    }

    @Override
    public String getDatabaseType() {
        return "Azure Cosmos DB";
    }
}
