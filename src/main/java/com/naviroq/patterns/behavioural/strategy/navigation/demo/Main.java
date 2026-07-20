package com.naviroq.patterns.behavioural.strategy.navigation.demo;

import com.naviroq.patterns.behavioural.strategy.navigation.NavigationApp;
import com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy.BikeRoute;
import com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy.CarRoute;
import com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy.PublicTransitRoute;
import com.naviroq.patterns.behavioural.strategy.navigation.routeStrategy.WalkingRoute;

public class Main {
    public static void main(String[] args) {
        NavigationApp app = new NavigationApp("Downtown Office");

        // Morning commute — by car
        app.setStrategy(new CarRoute());
        app.navigateTo("Tech Park", 12.5);

        // Lunch — walking to nearby cafe
        app.setStrategy(new WalkingRoute());
        app.navigateTo("Corner Cafe", 0.8);

        // Evening — bike home
        app.setStrategy(new BikeRoute());
        app.navigateTo("Riverside Apartments", 5.2);

        // Rainy day — take transit instead
        app.setStrategy(new PublicTransitRoute());
        app.navigateTo("Riverside Apartments", 5.2);
    }
}
