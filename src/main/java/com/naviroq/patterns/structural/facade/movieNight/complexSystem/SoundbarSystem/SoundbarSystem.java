package com.naviroq.patterns.structural.facade.movieNight.complexSystem.SoundbarSystem;

public class SoundbarSystem {
    public void on() {
        System.out.println("Soundbar: ON");
    }

    public void off() {
        System.out.println("Soundbar: OFF");
    }

    public void setMode(String mode) {
        System.out.println("Soundbar: Mode set to " + mode);
    }

    public void setVolume(int level) {
        System.out.println("Soundbar: Volume set to " + level);
    }
}
