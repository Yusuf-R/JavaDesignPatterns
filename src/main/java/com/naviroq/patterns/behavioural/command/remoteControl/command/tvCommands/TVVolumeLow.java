// tvCommands/TVVolumeLow.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.TV;

public class TVVolumeLow implements Command {
    private TV tv;

    public TVVolumeLow(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.saveState();
        tv.volumeLow();
    }

    @Override
    public void undo() {
        tv.restoreState();
    }

    @Override
    public String getDescription() {
        return "TV Volume LOW";
    }
}