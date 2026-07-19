package com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.sony;

import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.lg.LGTV;

public class SonyTV implements TV {
    @Override
    public void turnOn() {
        System.out.println("[Sony] Powering on.");
    }

    @Override
    public void turnOff() {
        System.out.println("[Sony] Powering off.");
    }

    @Override
    public void mute() {
        System.out.println("[SonyTV] Tv is Muted.");
    }

    @Override
    public void autoScan() {
        System.out.println();
        System.out.println("[SonyTV] Performing Scanning.");
        System.out.println("System is AutoScanning ...");
        System.out.println("48 Channels found ...");
        System.out.println("Saving Channel.");
        System.out.println("Scan Completed!!!.");
        System.out.println();
    }

    @Override
    public void standBy() {
        System.out.println("[SonyTV] Switching to standby mode on.");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("[Sony] Switching to channel " + channel);
    }

    @Override
    public void bright() {
        System.out.println("Picture quality improved");
    }

    @Override
    public void info() {
        System.out.println(LGTV.class + " in operation");
    }
}
