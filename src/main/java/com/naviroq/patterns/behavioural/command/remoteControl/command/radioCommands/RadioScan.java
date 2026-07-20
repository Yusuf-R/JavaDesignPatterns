// radioCommands/RadioScan.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Radio;

public class RadioScan implements Command {
    private Radio radio;

    public RadioScan(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.saveState();
        radio.scan();
    }

    @Override
    public void undo() {
        radio.restoreState();
    }

    @Override
    public String getDescription() {
        return "Radio SCAN";
    }
}