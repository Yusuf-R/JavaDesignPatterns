// NavigationApp.java
package com.naviroq.patterns.behavioural.strategy.navigation;

import com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy.RouteStrategy;

public class NavigationApp {
    private RouteStrategy strategy;
    private final String currentLocation;

    public NavigationApp(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
        System.out.println("\n[Switched to " + strategy.getStrategyName() + " mode]");
    }

    public void navigateTo(String destination, double distanceKm) {
        if (strategy == null) {
            System.out.println("No route mode selected!");
            return;
        }

        String route = strategy.buildRoute(currentLocation, destination);
        int time = strategy.getEstimatedTimeMinutes(distanceKm);

        System.out.println("From: " + currentLocation);
        System.out.println("To: " + destination);
        System.out.println("Mode: " + strategy.getStrategyName());
        System.out.println("Route: " + route);
        System.out.println("Distance: " + distanceKm + " km");
        System.out.println("Estimated time: " + time + " minutes");
    }
}