package com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy;

public interface RouteStrategy {
    String buildRoute(String from, String to);
    String getStrategyName();
    int getEstimatedTimeMinutes(double distanceKm);
}
