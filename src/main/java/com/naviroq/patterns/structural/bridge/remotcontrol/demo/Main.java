package com.naviroq.patterns.structural.bridge.remotcontrol.demo;

import com.naviroq.patterns.structural.bridge.remotcontrol.remote.advance.AdvanceRemote;
import com.naviroq.patterns.structural.bridge.remotcontrol.remote.basic.BasicRemote;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.lg.LGTV;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.samsung.SamsungTV;
import com.naviroq.patterns.structural.bridge.remotcontrol.tv.implementor.sony.SonyTV;

public class Main {
    public static void main(String[] args) {
        TV sony = new SonyTV();
        BasicRemote basicRemote = new BasicRemote(sony);
        basicRemote.powerOn();
        basicRemote.goToChannel(5);
        basicRemote.incrBrightness();
        basicRemote.info();

        System.out.println("\n\n");
        TV samsung = new SamsungTV();
        AdvanceRemote advancedRemote = new AdvanceRemote(samsung);
        advancedRemote.powerOn();
        advancedRemote.goToChannel(10);
        advancedRemote.standBy();
        advancedRemote.autoScan();
        advancedRemote.mute();
        advancedRemote.powerOff();
        advancedRemote.incrBrightness();


        System.out.println("\n\n");
        TV lg = new LGTV();
        AdvanceRemote advanceLGRemote = new AdvanceRemote(lg);
        advanceLGRemote.powerOn();
        advanceLGRemote.goToChannel(10);
        advanceLGRemote.autoScan();
        advancedRemote.info();
    }
}
