package com.naviroq.patterns.structural.bridge.powerdrill.types.mansory;

import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public class MasonryBit implements DrillBit {
    @Override
    public void operate(String material) {
        System.out.println("Masonry bit hammering through " + material);
    }
}
