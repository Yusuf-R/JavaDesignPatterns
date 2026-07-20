// tvCommands/TVVolumeMax.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.TV;

public class TVVolumeMax implements Command {
    private TV tv;

    public TVVolumeMax(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.saveState();
        tv.volumeMax();
    }

    @Override
    public void undo() {
        tv.restoreState();
    }

    @Override
    public String getDescription() {
        return "TV Volume MAX";
    }
}