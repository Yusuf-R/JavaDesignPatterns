package com.naviroq.patterns.structural.bridge.powerdrill.demo;

import com.naviroq.patterns.structural.bridge.powerdrill.PowerTool;
import com.naviroq.patterns.structural.bridge.powerdrill.types.DrillBit;
import com.naviroq.patterns.structural.bridge.powerdrill.types.mansory.MasonryBit;
import com.naviroq.patterns.structural.bridge.powerdrill.types.metallic.MetallicBit;
import com.naviroq.patterns.structural.bridge.powerdrill.types.wodden.WoodenBit;
import com.naviroq.patterns.structural.bridge.powerdrill.wired.WiredPowerTool;
import com.naviroq.patterns.structural.bridge.powerdrill.wireless.WirelessPowerTool;

public class Main {
    public static void main(String[] args) {

        // Create bits (the "how")
        DrillBit masonry = new MasonryBit();
        DrillBit wood = new WoodenBit();
        DrillBit metallic = new MetallicBit();

        // Create drills (the "what"), plug in any bit
        PowerTool wiredConcrete = new WiredPowerTool(masonry);
        PowerTool wirelessWood = new WirelessPowerTool(wood);
        PowerTool metallicBit = new WiredPowerTool(metallic);
        PowerTool cordlessConcrete = new WirelessPowerTool(masonry);  // Same bit, different drill

        // Use them
        wiredConcrete.use("concrete wall");
        wirelessWood.use("oak plank");
        metallicBit.use("drywall");
        cordlessConcrete.use("brick");

        // Swap bits at runtime
        System.out.println("\n--- Swapping bit on cordless drill ---");
        PowerTool cordlessScrew = new WirelessPowerTool(metallic);
        cordlessScrew.use("cabinet frame");
    }
}
