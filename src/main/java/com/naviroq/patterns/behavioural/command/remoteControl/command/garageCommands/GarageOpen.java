// garageCommands/GarageOpen.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.garageCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Garage;

public class GarageOpen implements Command {
    private Garage garage;

    public GarageOpen(Garage garage) {
        this.garage = garage;
    }

    @Override
    public void execute() {
        garage.saveState();
        garage.open();
    }

    @Override
    public void undo() {
        garage.restoreState();
    }

    @Override
    public String getDescription() {
        return "Garage OPEN";
    }
}