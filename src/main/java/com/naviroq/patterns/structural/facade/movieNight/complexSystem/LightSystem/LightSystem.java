package com.naviroq.patterns.structural.facade.movieNight.complexSystem.LightSystem;

public class LightSystem {
    public void dim(int level) {
        System.out.println("Lights: Dimming to " + level + "%");
    }

    public void on() {
        System.out.println("Lights: ON (100%)");
    }
}
