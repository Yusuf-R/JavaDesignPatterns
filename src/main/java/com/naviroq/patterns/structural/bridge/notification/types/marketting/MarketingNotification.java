package com.naviroq.patterns.structural.bridge.notification.types.marketting;

import com.naviroq.patterns.structural.bridge.notification.NotificationSystem;
import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class MarketingNotification extends NotificationSystem {

    public MarketingNotification(Channel channel) {
        super(channel);
    }

    @Override
    public void send(String message) {
        channel.deliver("📢 SPECIAL OFFER: " + message);
    }
}
