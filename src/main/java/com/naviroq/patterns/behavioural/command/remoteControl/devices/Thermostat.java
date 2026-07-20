package com.naviroq.patterns.behavioural.command.remoteControl.devices;

public class Thermostat {
    private int temperature;

    // State for undo
    private int wasTemperature;

    public Thermostat() {
        this.temperature = 22;
    }

    public void saveState() {
        wasTemperature = temperature;
    }

    public void restoreState() {
        temperature = wasTemperature;
        System.out.println("🌡️ Thermostat restored to " + temperature + "°C");
    }

    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println("🌡️ Thermostat set to " + temperature + "°C");
    }

    public int getTemperature() {
        return temperature;
    }
}