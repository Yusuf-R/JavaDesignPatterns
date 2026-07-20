package com.naviroq.patterns.behavioural.observer.weatherManager.listeners;

public interface Listeners {
    void update(float temperature, float humidity, float pressure);
}
