package com.naviroq.patterns.behavioural.command.remoteControl.devices;

public class TV {
    private boolean isOn;
    private boolean isStandby;
    private boolean isMuted;
    private int volume;

    // State snapshots for undo
    private boolean wasOn;
    private boolean wasStandby;
    private boolean wasMuted;
    private int wasVolume;

    public void saveState() {
        wasOn = isOn;
        wasStandby = isStandby;
        wasMuted = isMuted;
        wasVolume = volume;
    }

    public void restoreState() {
        isOn = wasOn;
        isStandby = wasStandby;
        isMuted = wasMuted;
        volume = wasVolume;
        System.out.println("📺 TV restored to previous state");
    }

    public void on() {
        isOn = true;
        isStandby = false;
        System.out.println("📺 TV ON");
    }

    public void off() {
        isOn = false;
        isStandby = false;
        isMuted = false;
        volume = 0;
        System.out.println("📺 TV OFF");
    }

    public void standby() {
        if (!isOn) return;
        isStandby = true;
        System.out.println("📺 TV STANDBY");
    }

    public void volumeUp() {
        if (!isOn || isStandby) return;
        volume++;
        System.out.println("📺 Volume: " + volume);
    }

    public void volumeDown() {
        if (!isOn || isStandby) return;
        volume = Math.max(0, volume - 1);
        System.out.println("📺 Volume: " + volume);
    }

    public void volumeMax() {
        if (!isOn || isStandby) return;
        volume = 100;
        System.out.println("📺 Volume MAX: " + volume);
    }

    public void volumeLow() {
        if (!isOn || isStandby) return;
        volume = 5;
        System.out.println("📺 Volume LOW: " + volume);
    }

    public void mute() {
        if (!isOn || isStandby) return;
        isMuted = !isMuted;
        System.out.println("📺 " + (isMuted ? "MUTED" : "UNMUTED"));
    }

    public void sleepAfter(int minutes) {
        if (!isOn) return;
        System.out.println("📺 Sleep in " + minutes + " min");
    }

    public boolean isOn() {
        return isOn;
    }
}