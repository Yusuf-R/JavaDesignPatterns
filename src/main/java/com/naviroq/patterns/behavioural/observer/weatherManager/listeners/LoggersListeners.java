package com.naviroq.patterns.behavioural.observer.weatherManager.listeners;

import java.time.LocalDateTime;

public class LoggersListeners implements Listeners {

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("[Logger] 📝 " + LocalDateTime.now()
                + " | Temp: " + temperature + "°C, Humidity: " + humidity
                + "%, Pressure: " + pressure + " hPa");
    }
}
