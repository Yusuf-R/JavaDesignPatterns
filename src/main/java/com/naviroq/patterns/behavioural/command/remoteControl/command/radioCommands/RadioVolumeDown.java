// radioCommands/RadioVolumeDown.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Radio;

public class RadioVolumeDown implements Command {
    private Radio radio;

    public RadioVolumeDown(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.saveState();
        radio.volumeDown();
    }

    @Override
    public void undo() {
        radio.restoreState();
    }

    @Override
    public String getDescription() {
        return "Radio Volume DOWN";
    }
}