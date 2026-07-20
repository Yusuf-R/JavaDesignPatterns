// lightCommands/LightOn.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.lightCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Light;

public class LightOn implements Command {
    private final Light light;

    public LightOn(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.saveState();
        light.turnOn();
    }

    @Override
    public void undo() {
        light.restoreState();
    }

    @Override
    public String getDescription() {
        return "Light ON";
    }
}