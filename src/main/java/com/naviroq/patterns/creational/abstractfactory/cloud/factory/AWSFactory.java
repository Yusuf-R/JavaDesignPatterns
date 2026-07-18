package com.naviroq.patterns.creational.abstractfactory.cloud.factory;

import com.naviroq.patterns.creational.abstractfactory.cloud.aws.EC2Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.aws.RDSDatabase;
import com.naviroq.patterns.creational.abstractfactory.cloud.aws.S3Storage;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Database;
import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;

public class AWSFactory implements CloudProviderFactory {
    @Override
    public Storage createStorage() {
        return new S3Storage();
    }

    @Override
    public Compute createCompute() {
        return new EC2Compute();
    }

    @Override
    public Database createDatabase() {
        return new RDSDatabase();
    }
}
