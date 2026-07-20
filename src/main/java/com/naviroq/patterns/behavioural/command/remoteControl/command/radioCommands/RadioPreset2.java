// radioCommands/RadioPreset2.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Radio;

public class RadioPreset2 implements Command {
    private final Radio radio;

    public RadioPreset2(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.saveState();
        radio.preset(2);
    }

    @Override
    public void undo() {
        radio.restoreState();
    }

    @Override
    public String getDescription() {
        return "Radio Preset 2";
    }
}