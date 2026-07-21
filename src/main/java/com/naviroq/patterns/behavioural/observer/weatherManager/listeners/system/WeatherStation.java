package com.naviroq.patterns.behavioural.observer.weatherManager.listeners.system;

import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.Listeners;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements WeatherSystem {
    private final List<Listeners> listeners;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        this.listeners = new ArrayList<>();
    }

     public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("\n[WeatherStation] 🌡️ NewState readings: " + temperature + "°C, "
                + humidity + "%, " + pressure + " hPa");
         notifyObservers();
    }

    @Override
    public void registerObserver(Listeners l) {
        listeners.add(l);
        System.out.println("[WeatherData] A new Listener registered: " + l.getClass().getSimpleName());

    }

    @Override
    public void removeObserver(Listeners l) {
        listeners.remove(l);
        System.out.println("[WeatherData] Listener removed: " + l.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Listeners l : listeners) {
            l.update(temperature, humidity, pressure);
        }
    }
}
