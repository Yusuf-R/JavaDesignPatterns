package com.naviroq.patterns.behavioural.observer.weatherManager.listeners;

public class WebDashboardListeners implements Listeners {
    private float temperature;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("[WebDashboard] 🌐 Weather: " + temperature + "°C, Pressure: " + pressure + " hPa");
    }
}
