// radioCommands/RadioPreset4.java
package com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.Radio;

public class RadioPreset3 implements Command {
    private final Radio radio;

    public RadioPreset3(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.saveState();
        radio.preset(3);
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