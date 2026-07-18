package com.naviroq.patterns.creational.abstractfactory.cloud.demo;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.CloudProviderFactory;

public class CloudApplication {

    private final Storage storage;
    private final Compute compute;
    private final Database database;

    // The client receives a factory and builds its toolkit
    public CloudApplication(CloudProviderFactory factory) {
        this.storage = factory.createStorage();
        this.compute = factory.createCompute();
        this.database = factory.createDatabase();
    }

    public void deploy() {
        System.out.println("=== Deploying Application ===");
        System.out.println("Storage: " + storage.getStorageType());
        System.out.println("Compute: " + compute.getComputeType());
        System.out.println("Database: " + database.getDatabaseType());
        System.out.println();

        storage.upload("app.jar", new byte[1024]);
        compute.startInstance("app-server-01");
        database.connect();
        database.executeQuery("CREATE TABLE users (id INT, name VARCHAR(255))");
        database.disconnect();
    }
}