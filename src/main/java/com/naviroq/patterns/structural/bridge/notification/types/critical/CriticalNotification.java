package com.naviroq.patterns.structural.bridge.notification.types.critical;

import com.naviroq.patterns.structural.bridge.notification.NotificationSystem;
import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class CriticalNotification extends NotificationSystem {

    public CriticalNotification(Channel channel) {
        super(channel);
    }

    @Override
    public void send(String message) {
        channel.deliver("\uD83D\uDEA8 CRITICAL: " + message);
    }
}
