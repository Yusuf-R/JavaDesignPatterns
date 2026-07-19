package com.naviroq.patterns.structural.bridge.remotcontrol.tv;

public interface TV {
    void turnOn();
    void turnOff();
    void mute();
    void autoScan();
    void standBy();
    void setChannel(int channel);
    void bright();
    void info();
}
