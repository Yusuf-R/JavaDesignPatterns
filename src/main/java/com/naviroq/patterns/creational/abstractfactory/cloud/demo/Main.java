package com.naviroq.patterns.creational.abstractfactory.cloud.demo;

import com.naviroq.patterns.creational.abstractfactory.cloud.factory.AWSFactory;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.AzureFactory;
import com.naviroq.patterns.creational.abstractfactory.cloud.factory.CloudProviderFactory;

public class Main {
    public static void main(String[] args) {

        System.out.println("----- AWS Deployment -----");
        CloudProviderFactory aws = new AWSFactory();
        CloudApplication awsApp = new CloudApplication(aws);
        awsApp.deploy();

        System.out.println("\n----- Azure Deployment -----");
        CloudProviderFactory azure = new AzureFactory();
        CloudApplication azureApp = new CloudApplication(azure);
        azureApp.deploy();
    }
}
