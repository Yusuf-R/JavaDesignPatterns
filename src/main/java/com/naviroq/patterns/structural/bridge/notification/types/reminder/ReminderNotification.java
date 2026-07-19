package com.naviroq.patterns.structural.bridge.notification.types.reminder;

import com.naviroq.patterns.structural.bridge.notification.NotificationSystem;
import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class ReminderNotification extends NotificationSystem {
    public ReminderNotification(Channel channel) {
        super(channel);
    }

    @Override
    public void send(String message) {
        channel.deliver("REMINDER: " + message);
    }
}
