// fanCommands/FanMedium.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.fanCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Fan;

public class FanMedium implements Command {
    private Fan fan;

    public FanMedium(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.saveState();
        fan.medium();
    }

    @Override
    public void undo() {
        fan.restoreState();
    }

    @Override
    public String getDescription() {
        return "Fan MEDIUM";
    }
}