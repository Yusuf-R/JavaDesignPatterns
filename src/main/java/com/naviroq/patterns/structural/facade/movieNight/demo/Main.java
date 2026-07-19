package com.naviroq.patterns.structural.facade.movieNight.demo;

import com.naviroq.patterns.structural.facade.movieNight.complexSystem.BluRayPlayer.BluRayPlayer;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.CurtainSystem.CurtainSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.LightSystem.LightSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.SoundbarSystem.SoundbarSystem;
import com.naviroq.patterns.structural.facade.movieNight.complexSystem.TvSystem.TvSystem;
import com.naviroq.patterns.structural.facade.movieNight.facadeSystem.HomeTheatreSystem;

import java.util.Scanner;

public class Main {

    enum Mode { IDLE, MOVIE, MUSIC }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create all the components (the messy part)
        TvSystem tv = new TvSystem();
        SoundbarSystem soundbar = new SoundbarSystem();
        BluRayPlayer bluRay = new BluRayPlayer();
        LightSystem lights = new LightSystem();
        CurtainSystem curtains = new CurtainSystem();

        // Create the Facade (the clean part)
        HomeTheatreSystem homeTheater = new HomeTheatreSystem(tv, soundbar, bluRay, lights, curtains);
        Mode currentMode = Mode.IDLE;

        while (true) {
            System.out.println("\n--- Mode: " + currentMode + " ---");
            System.out.println("1. Start Movie");
            System.out.println("2. Start Music");

            // Only show Stop Movie if in MOVIE mode
            if (currentMode == Mode.MOVIE) {
                System.out.println("3. Stop Movie");
            }

            // Only show Stop Music if in MUSIC mode
            if (currentMode == Mode.MUSIC) {
                System.out.println("4. Stop Music");
            }

            System.out.println("5. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (currentMode != Mode.IDLE) {
                        System.out.println("❌ Please stop the current activity first.");
                        break;
                    }
                    System.out.print("Enter movie name: ");
                    String movie = scanner.nextLine();
                    homeTheater.watchMovie(movie);
                    currentMode = Mode.MOVIE;
                    break;

                case "2":
                    if (currentMode != Mode.IDLE) {
                        System.out.println("❌ Please stop the current activity first.");
                        break;
                    }
                    System.out.print("Enter song name: ");
                    String song = scanner.nextLine();
                    homeTheater.listenToMusic(song);
                    currentMode = Mode.MUSIC;
                    break;

                case "3":
                    if (currentMode == Mode.MOVIE) {
                        homeTheater.stopMovie();
                        currentMode = Mode.IDLE;
                    } else {
                        System.out.println("❌ No movie is playing.");
                    }
                    break;

                case "4":
                    if (currentMode == Mode.MUSIC) {
                        homeTheater.stopMusic();
                        currentMode = Mode.IDLE;
                    } else {
                        System.out.println("❌ No music is playing.");
                    }
                    break;

                case "5":
                    System.out.println("👋 Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}