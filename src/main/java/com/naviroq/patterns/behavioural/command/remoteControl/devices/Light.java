package com.naviroq.patterns.behavioural.command.remoteControl.devices;

public class Light {
    private final String location;
    private boolean isOn;

    // State for undo
    private boolean wasOn;

    public Light(String location) {
        this.location = location;
    }

    public void saveState() {
        wasOn = isOn;
    }

    public void restoreState() {
        isOn = wasOn;
        System.out.println("💡 [" + location + "] Light restored");
    }

    public void turnOn() {
        isOn = true;
        System.out.println("💡 [" + location + "] Light is ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("💡 [" + location + "] Light is OFF");
    }

    public boolean isOn() {
        return isOn;
    }
}