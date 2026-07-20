package com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy;

public class BikeRoute implements RouteStrategy {
    @Override
    public String buildRoute(String from, String to) {
        return from + " → Bike Lane on Oak Ave → Riverside Trail → " + to;
    }

    @Override
    public String getStrategyName() {
        return "🚲 Bicycle";
    }

    @Override
    public int getEstimatedTimeMinutes(double distanceKm) {
        return (int) (distanceKm * 4);  // ~15 km/h average
    }
}
