// radioCommands/RadioPreset2.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Radio;

public class RadioPreset4 implements Command {
    private final Radio radio;

    public RadioPreset4(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.saveState();
        radio.preset(4);
    }

    @Override
    public void undo() {
        radio.restoreState();
    }

    @Override
    public String getDescription() {
        return "Radio Preset 3";
    }
}