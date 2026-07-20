// fanCommands/FanOff.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.fanCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Fan;

public class FanOff implements Command {
    private Fan fan;

    public FanOff(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.saveState();
        fan.off();
    }

    @Override
    public void undo() {
        fan.restoreState();
    }

    @Override
    public String getDescription() {
        return "Fan OFF";
    }
}