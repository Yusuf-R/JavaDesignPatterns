package com.naviroq.patterns.creational.abstractfactory.cloud.factory;

import com.naviroq.patterns.creational.abstractfactory.cloud.azure.AzureVMCompute;
import com.naviroq.patterns.creational.abstractfactory.cloud.azure.BlobStorage;
import com.naviroq.patterns.creational.abstractfactory.cloud.azure.CosmosDatabase;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;

public class AzureFactory implements CloudProviderFactory {
    @Override
    public Storage createStorage() {
        return new BlobStorage();
    }

    @Override
    public Compute createCompute() {
        return new AzureVMCompute();
    }

    @Override
    public Database createDatabase() {
        return new CosmosDatabase();
    }
}
