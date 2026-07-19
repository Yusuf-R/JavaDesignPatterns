package com.naviroq.patterns.structural.facade.movieNight.complexSystem.BluRayPlayer;

public class BluRayPlayer {
    public void on() {
        System.out.println("Blu-ray: ON");
    }

    public void off() {
        System.out.println("Blu-ray: OFF");
    }

    public void play(String movie) {
        System.out.println("Blu-ray: Playing " + movie);
    }

    public void eject() {
        System.out.println("Blu-ray: Ejecting disc");
    }
}
