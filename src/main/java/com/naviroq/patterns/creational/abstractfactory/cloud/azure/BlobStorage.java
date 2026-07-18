package com.naviroq.patterns.creational.abstractfactory.cloud.azure;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;

public class BlobStorage implements Storage {
    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("[Azure Blob] Uploading " + fileName + " to container");
    }

    @Override
    public byte[] download(String fileName) {
        System.out.println("[Azure Blob] Downloading " + fileName + " from container");
        return new byte[0];
    }

    @Override
    public String getStorageType() {
        return "Azure Blob Storage";
    }
}
