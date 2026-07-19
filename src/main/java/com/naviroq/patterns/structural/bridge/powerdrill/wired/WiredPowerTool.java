package com.naviroq.patterns.structural.bridge.powerdrill.wired;

import com.naviroq.patterns.structural.bridge.powerdrill.PowerTool;
import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public class WiredPowerTool extends PowerTool {

    public WiredPowerTool(DrillBit bit) {
        super(bit);
    }

    @Override
    public void use(String material) {
        System.out.print("[Wireless Tool] ");
        bit.operate(material);
    }


}
