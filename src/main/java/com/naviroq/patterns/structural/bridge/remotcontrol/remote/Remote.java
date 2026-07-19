package com.naviroq.patterns.structural.bridge.remotcontrol.remote;

import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;

public abstract class Remote {
    protected TV tv; // The "bridge" to the implementation

    public Remote(TV tv) {
        this.tv = tv;
    }

    public void powerOn() {
        tv.turnOn();
    }

    public void powerOff() {
        tv.turnOff();
    }

    public void goToChannel(int channel) {
        tv.setChannel(channel);
    }
}
