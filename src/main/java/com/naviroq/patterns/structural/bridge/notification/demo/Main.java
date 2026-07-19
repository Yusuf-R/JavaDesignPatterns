package com.naviroq.patterns.structural.bridge.notification.demo;

import com.naviroq.patterns.structural.bridge.notification.NotificationSystem;
import com.naviroq.patterns.structural.bridge.notification.channles.Channel;
import com.naviroq.patterns.structural.bridge.notification.channles.email.EmailChannel;
import com.naviroq.patterns.structural.bridge.notification.channles.slack.SlackChannel;
import com.naviroq.patterns.structural.bridge.notification.channles.sms.SMSChannel;
import com.naviroq.patterns.structural.bridge.notification.types.alert.AlertNotification;
import com.naviroq.patterns.structural.bridge.notification.types.critical.CriticalNotification;
import com.naviroq.patterns.structural.bridge.notification.types.marketting.MarketingNotification;
import com.naviroq.patterns.structural.bridge.notification.types.reminder.ReminderNotification;

public class Main {
    public static void main(String[] args) {
        // Create channels (the "how")
        Channel email = new EmailChannel();
        Channel sms = new SMSChannel();
        Channel slack = new SlackChannel();

        // Create notifications (the "what"), plug in any channel
        NotificationSystem alertEmail = new AlertNotification(email);
        NotificationSystem alertSMS = new AlertNotification(sms);
        NotificationSystem reminderSlack = new ReminderNotification(slack);
        NotificationSystem marketingEmail = new MarketingNotification(email);
        NotificationSystem criticalMessage = new CriticalNotification(slack);

        // Send
        alertEmail.send("Server CPU at 95%");
        alertSMS.send("Database connection lost");
        reminderSlack.send("Deploy scheduled for 2 PM");
        marketingEmail.send("50% off all plans this weekend!");
        criticalMessage.send("Payment gateway timeout");
    }
}
