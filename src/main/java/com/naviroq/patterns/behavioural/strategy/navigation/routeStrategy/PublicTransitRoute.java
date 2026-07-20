package com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy;

public class PublicTransitRoute implements RouteStrategy {
    @Override
    public String buildRoute(String from, String to) {
        return from + " → Bus 42 → Metro Line 3 → Transfer → Bus 17 → " + to;
    }

    @Override
    public String getStrategyName() {
        return "🚌 Public Transit";
    }

    @Override
    public int getEstimatedTimeMinutes(double distanceKm) {
        return (int) (distanceKm * 2.5 + 15);  // Slower + wait time
    }
}
