// tvCommands/TVVolumeUp.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.TV;

public class TVVolumeUp implements Command {
    private final TV tv;

    public TVVolumeUp(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.saveState();
        tv.volumeUp();
    }

    @Override
    public void undo() {
        tv.restoreState();
    }

    @Override
    public String getDescription() {
        return "TV Volume UP";
    }
}