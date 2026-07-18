package com.naviroq.patterns.creational.prototype.monsters;

public class Monster {
    private final String id;
    private String name;
    private GameStats gameStats;
    private Weapon weapon;
    private Appearance appearance;
    private int experienceValue;

    // --- Normal Constructor ---
    public Monster(String id, String name, GameStats gameStats, Weapon weapon, Appearance appearance, int experienceValue) {
        this.id = id;
        this.name = name;
        this.gameStats = gameStats;
        this.weapon = weapon;
        this.appearance = appearance;
        this.experienceValue = experienceValue;
    }

    // --- COPY CONSTRUCTOR (DEEP COPY) ---
    public Monster(Monster other) {
        this.id = other.id + "-clone";
        this.name = other.name;
        this.gameStats = new GameStats(other.gameStats);           // DEEP COPY
        this.weapon = new Weapon(other.weapon);        // DEEP COPY
        this.appearance = new Appearance(other.appearance); // DEEP COPY
        this.experienceValue = other.experienceValue;
    }

    // --- The Prototype Method ---
    public Monster clonePrototype() {
        return new Monster(this);
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public GameStats getStats() { return gameStats; }
    public void setStats(GameStats gameStats) { this.gameStats = gameStats; }
    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
    public Appearance getAppearance() { return appearance; }
    public void setAppearance(Appearance appearance) { this.appearance = appearance; }
    public int getExperienceValue() { return experienceValue; }
    public void setExperienceValue(int experienceValue) { this.experienceValue = experienceValue; }

    @Override
    public String toString() {
        return "Monster{id='" + id + "', name='" + name + "', gameStats=" + gameStats +
                ", weapon=" + weapon + ", appearance=" + appearance +
                ", exp=" + experienceValue + "}";
    }
}