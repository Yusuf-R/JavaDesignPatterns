package com.naviroq.patterns.creational.abstractfactory.cloud.common;

public interface Storage {
    void upload(String fileName, byte[] data);
    byte[] download(String fileName);
    String getStorageType();  // For debugging/demo
}
