package com.naviroq.patterns.structural.bridge.notification.channles.sms;

import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class SMSChannel implements Channel {

    @Override
    public void deliver(String content) {
        String truncated = content.length() > 160 ? content.substring(0, 157) + "..." : content;
        System.out.println("\n[SMS] " + truncated + "\n");
    }
}
