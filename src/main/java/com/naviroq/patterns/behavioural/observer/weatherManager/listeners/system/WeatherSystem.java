package com.naviroq.patterns.behavioural.observer.weatherManager.listeners.system;

import com.naviroq.patterns.behavioural.observer.weatherManager.listeners.Listeners;

public interface WeatherSystem {
    void registerObserver(Listeners l);
    void removeObserver(Listeners l);
    void notifyObservers();
}
