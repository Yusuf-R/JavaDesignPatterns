package com.naviroq.patterns.behavioural.observer.weatherManager.listeners;

public class AlertSystemListeners implements Listeners {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        if (temperature > 35) {
            System.out.println("[AlertSystem] 🚨 HEAT WARNING: " + temperature + "°C exceeds safe threshold!");
        }
        if (pressure < 980) {
            System.out.println("[AlertSystem] ⛈️ STORM ALERT: Low pressure " + pressure + " hPa");
        }
    }
}
