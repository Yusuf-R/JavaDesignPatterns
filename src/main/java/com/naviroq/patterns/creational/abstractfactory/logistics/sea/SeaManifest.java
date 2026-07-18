package com.naviroq.patterns.creational.abstractfactory.logistics.sea;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;

public class SeaManifest implements Manifest {
    @Override
    public void print() {
        System.out.println("  📜 Printing SEA manifest (Bill of Lading for Ship).");
    }
}
