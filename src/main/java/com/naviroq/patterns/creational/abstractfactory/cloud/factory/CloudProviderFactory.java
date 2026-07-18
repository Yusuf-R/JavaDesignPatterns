package com.naviroq.patterns.creational.abstractfactory.cloud.factory;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;

public interface CloudProviderFactory {
    Storage createStorage();
    Compute createCompute();
    Database createDatabase();
}
