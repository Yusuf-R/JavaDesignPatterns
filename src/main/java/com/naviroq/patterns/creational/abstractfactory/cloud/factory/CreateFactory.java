package com.naviroq.patterns.creational.abstractfactory.cloud.factory;

public class CreateFactory {
    public CloudProviderFactory getService (ServiceInputs type) {
        if (type == null) {
            throw new IllegalArgumentException("Service type can not be null");
        }
        return switch (type) {
            case aws -> new AWSFactory();
            case azure -> new AzureFactory();
            default -> throw new IllegalStateException("Unexpected service value: " + type);
        };
    }
}
