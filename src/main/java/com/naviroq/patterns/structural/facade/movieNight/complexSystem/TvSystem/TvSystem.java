package com.naviroq.patterns.structural.facade.movieNight.complexSystem.TvSystem;

public class TvSystem {
    public void on() {
        System.out.println("TV: ON");
    }

    public void off() {
        System.out.println("TV: OFF");
    }

    public void setInput(String input) {
        System.out.println("TV: Input set to " + input);
    }

    public void setVolume(int level) {
        System.out.println("TV: Volume set to " + level);
    }
}
