package com.naviroq.patterns.behavioural.command.remoteControl.devices;

public class Garage {
    private boolean isOpen;
    private boolean lightOn;

    // State for undo
    private boolean wasOpen;
    private boolean wasLightOn;

    public void saveState() {
        wasOpen = isOpen;
        wasLightOn = lightOn;
    }

    public void restoreState() {
        isOpen = wasOpen;
        lightOn = wasLightOn;
        System.out.println("🚗 Garage restored");
    }

    public void open() {
        isOpen = true;
        lightOn = true;
        System.out.println("🚗 Garage DOOR OPENED | Light: ON");
    }

    public void close() {
        isOpen = false;
        lightOn = false;
        System.out.println("🚗 Garage DOOR CLOSED | Light: OFF");
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isLightOn() {
        return lightOn;
    }
}