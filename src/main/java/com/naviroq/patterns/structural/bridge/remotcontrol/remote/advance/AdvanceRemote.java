package com.naviroq.patterns.structural.bridge.remotcontrol.remote.advance;

import com.naviroq.patterns.structural.bridge.remotcontrol.remote.Remote;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;

public class AdvanceRemote extends Remote {
    public AdvanceRemote(TV tv) {
        super(tv);
    }

    public void mute() {
        tv.mute();
        System.out.println("Muted.");
    }

    public void standBy(){
        tv.standBy();
        System.out.println("StanBy mode activated.");
    }

    public void autoScan() {
        tv.autoScan();
    }

    public void incrBrightness() {
        tv.bright();
    }

    public void info() {
        tv.info();
    }
}
