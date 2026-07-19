package com.naviroq.patterns.structural.bridge.powerdrill.types.wodden;

import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public class WoodenBit implements DrillBit {

    @Override
    public void operate(String material) {
        System.out.println("Wood bit drilling smoothly through " + material);
    }
}
