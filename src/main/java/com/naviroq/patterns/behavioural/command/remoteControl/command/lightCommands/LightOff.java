// lightCommands/LightOff.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.lightCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Light;

public class LightOff implements Command {
    private final Light light;

    public LightOff(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.saveState();
        light.turnOff();
    }

    @Override
    public void undo() {
        light.restoreState();
    }

    @Override
    public String getDescription() {
        return "Light OFF";
    }
}