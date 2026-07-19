package com.naviroq.patterns.structural.bridge.remotcontrol.remote.basic;

import com.naviroq.patterns.structural.bridge.remotcontrol.remote.Remote;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;

public class BasicRemote extends Remote {
    public BasicRemote(TV tv) {
        super(tv);
    }

    public void incrBrightness() {
        tv.bright();
    }

    public void info() {
        tv.info();
    }
}
