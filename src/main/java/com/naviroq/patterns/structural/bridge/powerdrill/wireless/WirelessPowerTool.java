package com.naviroq.patterns.structural.bridge.powerdrill.wireless;

import com.naviroq.patterns.structural.bridge.powerdrill.PowerTool;
import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public class WirelessPowerTool extends PowerTool {

    public WirelessPowerTool(DrillBit bit) {
        super(bit);
    }
    @Override
    public void use(String material) {
        System.out.print("[Wired Tool] ");
        bit.operate(material);
    }
}
