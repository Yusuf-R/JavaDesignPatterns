// thermostatCommands/ThermostatSet.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.thermostatCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Thermostat;

public class ThermostatSet implements Command {
    private Thermostat thermostat;
    private int targetTemperature;

    public ThermostatSet(Thermostat thermostat, int targetTemperature) {
        this.thermostat = thermostat;
        this.targetTemperature = targetTemperature;
    }

    @Override
    public void execute() {
        thermostat.saveState();
        thermostat.setTemperature(targetTemperature);
    }

    @Override
    public void undo() {
        thermostat.restoreState();
    }

    @Override
    public String getDescription() {
        return "Thermostat SET " + targetTemperature + "°C";
    }
}