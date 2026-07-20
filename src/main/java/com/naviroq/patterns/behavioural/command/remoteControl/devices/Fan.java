package com.naviroq.patterns.behavioural.command.remoteControl.devices;

public class Fan {
    private final String location;
    private int speed;  // 0 = off, 1 = low, 2 = medium, 3 = high

    // State for undo
    private int wasSpeed;

    public Fan(String location) {
        this.location = location;
    }

    public void saveState() {
        wasSpeed = speed;
    }

    public void restoreState() {
        speed = wasSpeed;
        System.out.println("🌀 [" + location + "] Fan restored");
    }

    public void high() {
        speed = 3;
        System.out.println("🌀 [" + location + "] Fan is HIGH");
    }

    public void medium() {
        speed = 2;
        System.out.println("🌀 [" + location + "] Fan is MEDIUM");
    }

    public void low() {
        speed = 1;
        System.out.println("🌀 [" + location + "] Fan is LOW");
    }

    public void off() {
        speed = 0;
        System.out.println("🌀 [" + location + "] Fan is OFF");
    }

    public int getSpeed() {
        return speed;
    }
}