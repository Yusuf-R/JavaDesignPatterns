package com.naviroq.patterns.structural.facade.movieNight.facadeSystem;

import com.naviroq.patterns.structural.bridge.remotcontrol.tv.TV;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.BluRayPlayer.BluRayPlayer;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.CurtainSystem.CurtainSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.LightSystem.LightSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.SoundbarSystem.SoundbarSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.TvSystem.TvSystem;

public class HomeTheatreSystem {
    private final TvSystem tv;
    private final SoundbarSystem soundbar;
    private final BluRayPlayer bluRay;
    private final LightSystem lights;
    private final CurtainSystem curtains;

    public HomeTheatreSystem(TvSystem tv, SoundbarSystem soundbar, BluRayPlayer bluRay, LightSystem lights, CurtainSystem curtains) {
        this.tv = tv;
        this.soundbar = soundbar;
        this.bluRay = bluRay;
        this.lights = lights;
        this.curtains = curtains;
    }

    public void watchMovie(String movie) {
        System.out.println("\n🎬 Starting Movie Experience...");
        lights.dim(20);
        curtains.close();
        tv.on();
        tv.setInput("HDMI 2");
        tv.setVolume(0);
        soundbar.on();
        soundbar.setMode("Surround");
        soundbar.setVolume(30);
        bluRay.on();
        bluRay.play(movie);
        System.out.println("✅ Movie started! Enjoy.\n");
    }

    public void stopMovie() {
        System.out.println("\n⏹️  Stopping Movie...");
        bluRay.eject();
        bluRay.off();
        soundbar.off();
        tv.off();
        lights.on();
        curtains.open();
        System.out.println("✅ Movie stopped. System ready for normal use.\n");
    }

    public void listenToMusic(String song) {
        System.out.println("\n🎵 Starting Music: " + song + " ...");
        lights.dim(50);
        curtains.close();
        tv.on();
        tv.setInput("Bluetooth");
        soundbar.on();
        soundbar.setMode("Stereo");
        soundbar.setVolume(25);
        System.out.println("✅ Now playing: " + song + "\n");
    }

    public void stopMusic() {
        System.out.println("\n🎵 Stopping Music...");
        soundbar.off();
        tv.off();
        lights.on();
        curtains.open();
        System.out.println("✅ Music stopped.\n");
    }
}