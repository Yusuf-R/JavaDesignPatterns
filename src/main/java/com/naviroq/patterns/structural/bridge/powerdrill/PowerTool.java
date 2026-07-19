package com.naviroq.patterns.structural.bridge.powerdrill;

import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;

public abstract class PowerTool {
    protected DrillBit bit;  // A power tool should have a DrillBit --> this is the BRIDGE -- has-a composition

    public PowerTool(DrillBit bit) {
        this.bit = bit;  // --> has-a relationship, the drill Bit is independent of the power tool
    }

    public abstract void use(String material);
}
