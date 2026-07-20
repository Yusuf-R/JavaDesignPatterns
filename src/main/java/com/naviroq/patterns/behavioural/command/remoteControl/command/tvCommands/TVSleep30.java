// tvCommands/TVSleep30.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.TV;

public class TVSleep30 implements Command {
    private TV tv;

    public TVSleep30(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.saveState();
        tv.sleepAfter(30);
    }

    @Override
    public void undo() {
        tv.restoreState();
    }

    @Override
    public String getDescription() {
        return "TV Sleep 30min";
    }
}