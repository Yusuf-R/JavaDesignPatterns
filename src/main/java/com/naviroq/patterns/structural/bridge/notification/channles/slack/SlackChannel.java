package com.naviroq.patterns.structural.bridge.notification.channles.slack;

import com.naviroq.patterns.structural.bridge.notification.channles.Channel;

public class SlackChannel implements Channel {
    @Override
    public void deliver(String content) {
        System.out.println("\n[SLACK] :\uD83D\uDD14: " + content + "\n");
    }
}
