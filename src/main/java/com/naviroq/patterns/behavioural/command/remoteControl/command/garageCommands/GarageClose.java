// garageCommands/GarageClose.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.garageCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Garage;

public class GarageClose implements Command {
    private final Garage garage;

    public GarageClose(Garage garage) {
        this.garage = garage;
    }

    @Override
    public void execute() {
        garage.saveState();
        garage.close();
    }

    @Override
    public void undo() {
        garage.restoreState();
    }

    @Override
    public String getDescription() {
        return "Garage CLOSE";
    }
}