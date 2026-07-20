package com.naviroq.patterns.behavioural.observer.weatherManager.demo;

import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.AlertSystemListeners;
import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.LoggersListeners;
import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.PhoneListeners;
import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.WebDashboardListeners;
import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.system.WeatherStation;

public class Main {
    public static void main(String[] args) {
        // The subject (publisher)
        WeatherStation weatherStation = new WeatherStation();

        // The observers (subscribers)
        PhoneListeners phone = new PhoneListeners();
        WebDashboardListeners web = new WebDashboardListeners();
        AlertSystemListeners alerts = new AlertSystemListeners();
        LoggersListeners logger = new LoggersListeners();

        // Register observers
        weatherStation.registerObserver(phone);
        weatherStation.registerObserver(web);
        weatherStation.registerObserver(alerts);
        weatherStation.registerObserver(logger);

        // Simulate weather changes
        System.out.println("=== MORNING ===");
        weatherStation.setMeasurements(22.5f, 65, 1013);

        System.out.println("\n=== AFTERNOON (getting hot) ===");
        weatherStation.setMeasurements(36.0f, 45, 1008);

        System.out.println("\n=== EVENING (storm approaching) ===");
        weatherStation.setMeasurements(28.0f, 80, 978);

        // Remove phone display, add it back later
        System.out.println("\n--- Removing phone display ---");
        weatherStation.removeObserver(phone);

        System.out.println("\n=== NIGHT (phone unsubscribed) ===");
        weatherStation.setMeasurements(20.0f, 70, 985);

        System.out.println("\n--- Re-adding phone display ---");
        weatherStation.registerObserver(phone);

        System.out.println("\n=== MIDNIGHT ===");
        weatherStation.setMeasurements(18.5f, 75, 990);
    }
}
