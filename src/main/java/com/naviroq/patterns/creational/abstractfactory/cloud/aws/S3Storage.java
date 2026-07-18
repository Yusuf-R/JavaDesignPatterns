package com.naviroq.patterns.creational.abstractfactory.cloud.aws;

import com.naviroq.patterns.creational.abstractfactory.cloud.common.Storage;

public class S3Storage implements Storage {
    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("[AWS S3] Uploading " + fileName + " to bucket");
    }

    @Override
    public byte[] download(String fileName) {
        System.out.println("[AWS S3] Downloading " + fileName + " from bucket");
        return new byte[0];
    }

    @Override
    public String getStorageType() {
        return "AWS S3 Object Storage";
    }
}