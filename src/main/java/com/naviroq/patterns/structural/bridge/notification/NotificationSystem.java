package com.naviroq.patterns.structural.bridge.notification;

import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public abstract class NotificationSystem {
    protected Channel channel;  // THE BRIDGE

    public NotificationSystem(Channel channel) {
        this.channel = channel;
    }

    public abstract void send(String message);
}
