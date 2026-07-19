package com.naviroq.patterns.structural.bridge.notification.types.alert;

import com.naviroq.patterns.structural.bridge.notification.NotificationSystem;
import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class AlertNotification extends NotificationSystem {

    // constructor
    public AlertNotification(Channel channel) {
        super(channel);
    }

    @Override
    public void send(String message) {
        // channel is available to us as direct inheritance
        channel.deliver("🚨 ALERT: " + message);
    }
}
