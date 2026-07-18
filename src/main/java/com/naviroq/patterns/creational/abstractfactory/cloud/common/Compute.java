package com.naviroq.patterns.creational.abstractfactory.cloud.common;

public interface Compute {
    void startInstance(String instanceId);
    void stopInstance(String instanceId);
    String getComputeType();
}
