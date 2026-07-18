package com.naviroq.patterns.creational.prototype.monsters;

public class GameStats {
    private int health;
    private int mana;
    private int speed;
    private int strength;

    public GameStats(int health, int mana, int speed, int strength) {
        this.health = health;
        this.mana = mana;
        this.speed = speed;
        this.strength = strength;
    }

    // --- COPY CONSTRUCTOR (Deep Copy) ---
    public GameStats(GameStats other) {
        this.health = other.health;
        this.mana = other.mana;
        this.speed = other.speed;
        this.strength = other.strength;
    }

    // Getters & Setters
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public String toString() {
        return "GameStats{HP=" + health + ", MP=" + mana + ", SPD=" + speed + ", STR=" + strength + "}";
    }
}