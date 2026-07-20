package com.naviroq.patterns.behavioural.command.remoteControl.devices;

import java.util.ArrayList;
import java.util.List;

public class Radio {
    private boolean isOn;
    private int volume;
    private double currentFrequency;
    private List<Double> presets = new ArrayList<>();
    private static final double[] SCAN_FREQUENCIES = {88.5, 92.3, 96.1, 98.5, 101.1, 104.7};

    // State for undo
    private boolean wasOn;
    private int wasVolume;
    private double wasFrequency;
    private List<Double> wasPresets = new ArrayList<>();

    public void saveState() {
        wasOn = isOn;
        wasVolume = volume;
        wasFrequency = currentFrequency;
        wasPresets = new ArrayList<>(presets);
    }

    public void restoreState() {
        isOn = wasOn;
        volume = wasVolume;
        currentFrequency = wasFrequency;
        presets = new ArrayList<>(wasPresets);
        System.out.println("📻 Radio restored to previous state");
    }

    public void on() {
        isOn = true;
        System.out.println("📻 Radio ON");
    }

    public void off() {
        isOn = false;
        System.out.println("📻 Radio OFF");
    }

    public void volumeUp() {
        if (!isOn) {
            System.out.println("📻 Radio is OFF — turn on first");
            return;
        }
        volume = Math.min(volume + 5, 100);
        System.out.println("📻 Volume: " + volume);
    }

    public void volumeDown() {
        if (!isOn) return;
        volume = Math.max(volume - 5, 0);
        System.out.println("📻 Volume: " + volume);
    }

    public void scan() {
        if (!isOn) {
            System.out.println("📻 Radio is OFF — turn on first");
            return;
        }
        System.out.println("📻 Scanning frequencies...");
        presets.clear();
        for (int i = 0; i < SCAN_FREQUENCIES.length && presets.size() < 4; i++) {
            presets.add(SCAN_FREQUENCIES[i]);
            System.out.println("  Found: " + SCAN_FREQUENCIES[i] + " FM [Preset " + (i + 1) + "]");
        }
        if (!presets.isEmpty()) {
            currentFrequency = presets.get(0);
        }
    }

    public void preset(int number) {
        if (!isOn) return;
        if (number < 1 || number > presets.size()) {
            System.out.println("📻 Preset " + number + " not set");
            return;
        }
        currentFrequency = presets.get(number - 1);
        System.out.println("📻 Playing Preset " + number + ": " + currentFrequency + " FM");
    }

    public boolean isOn() {
        return isOn;
    }
}