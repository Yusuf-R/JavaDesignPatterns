// tvCommands/TVVolumeUp.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.TV;

public class TVVolumeDown implements Command {
    private final TV tv;

    public TVVolumeDown(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.saveState();
        tv.volumeDown();
    }

    @Override
    public void undo() {
        tv.restoreState();
    }

    @Override
    public String getDescription() {
        return "TV Volume DOWN";
    }
}