package com.naviroq.patterns.behavioural.observer.weatherManager.listeners;

public class PhoneListeners implements Listeners {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("[PhoneDisplay] 📱 Current: " + temperature + "°C, " + humidity + "% humidity");
    }
}
