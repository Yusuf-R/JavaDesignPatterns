package com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.lg;

import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;

public class LGTV implements TV {
    @Override
    public void turnOn() {
        System.out.println("[LGTV] Powering on.");
    }

    @Override
    public void turnOff() {
        System.out.println("[LGTV] Powering on.");
    }

    @Override
    public void mute() {
        System.out.println("[LGTV] Tv is Muted.");
    }

    @Override
    public void autoScan() {
        System.out.println();
        System.out.println("[LGTV] Performing Scanning.");
        System.out.println("System is AutoScanning ...");
        System.out.println("48 Channels found ...");
        System.out.println("Saving Channel.");
        System.out.println("Scan Completed!!!.");
        System.out.println();
    }

    @Override
    public void standBy() {
        System.out.println("[LGTV] Switching to standby mode on.");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("[LGTV] Switching to channel " + channel);
    }

    @Override
    public void bright() {
        System.out.println("Picture quality improved\n");
    }

    @Override
    public void info() {
        System.out.println(LGTV.class + " in operation\n");
    }
}
