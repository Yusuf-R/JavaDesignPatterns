package com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy;

public class CarRoute implements RouteStrategy {
    @Override
    public String buildRoute(String from, String to) {
        return from + " → Highway 101 → Main St → " + to;
    }

    @Override
    public String getStrategyName() {
        return "🚗 Car";
    }

    @Override
    public int getEstimatedTimeMinutes(double distanceKm) {
        return (int) (distanceKm * 1.2);  // ~50 km/h average
    }
}
