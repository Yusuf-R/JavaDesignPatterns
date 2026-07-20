// fanCommands/FanHigh.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.fanCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Fan;

public class FanHigh implements Command {
    private final Fan fan;

    public FanHigh(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.saveState();
        fan.high();
    }

    @Override
    public void undo() {
        fan.restoreState();
    }

    @Override
    public String getDescription() {
        return "Fan HIGH";
    }
}