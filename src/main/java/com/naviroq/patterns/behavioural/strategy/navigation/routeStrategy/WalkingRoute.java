package com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy;

public class WalkingRoute implements RouteStrategy {
    @Override
    public String buildRoute(String from, String to) {
        return from + " → Pedestrian Plaza → Central Park Path → " + to;
    }

    @Override
    public String getStrategyName() {
        return "🚶 Walking";
    }

    @Override
    public int getEstimatedTimeMinutes(double distanceKm) {
        return (int) (distanceKm * 12);  // ~5 km/h average
    }

}
