package com.naviroq.patterns.creational.abstractfactory.cloud.aws;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;

public class EC2Compute implements Compute {
    @Override
    public void startInstance(String instanceId) {
        System.out.println("[AWS EC2] Starting instance " + instanceId);
    }

    @Override
    public void stopInstance(String instanceId) {
        System.out.println("[AWS EC2] Stopping instance " + instanceId);
    }

    @Override
    public String getComputeType() {
        return "AWS EC2 Virtual Machine";
    }
}
