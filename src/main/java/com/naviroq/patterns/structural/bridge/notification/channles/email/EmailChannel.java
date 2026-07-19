package com.naviroq.patterns.structural.bridge.notification.channles.email;

import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class EmailChannel implements Channel {
    @Override
    public void deliver(String content) {
        System.out.println("\n[EMAIL] Subject: Alert\nBody: " + content + "\n");
    }
}
