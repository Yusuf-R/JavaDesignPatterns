package com.naviroq.patterns.structural.bridge.powerdrill.types.metallic;

import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public class MetallicBit implements DrillBit {
    @Override
    public void operate(String material) {
        System.out.println("Screwdriver bit driving screw into " + material);
    }
}
