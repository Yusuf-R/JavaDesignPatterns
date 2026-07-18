package com.naviroq.patterns.creational.abstractfactory.cloud.azure;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Compute;

public class AzureVMCompute implements Compute {
    @Override
    public void startInstance(String instanceId) {
        System.out.println("[Azure VM] Starting virtual machine " + instanceId);
    }

    @Override
    public void stopInstance(String instanceId) {
        System.out.println("[Azure VM] Deallocating virtual machine " + instanceId);
    }

    @Override
    public String getComputeType() {
        return "Azure Virtual Machine";
    }
}
