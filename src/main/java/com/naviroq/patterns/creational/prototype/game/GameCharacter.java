// GameCharacter.java — the prototype
package com.naviroq.patterns.creational.prototype.game;

public class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int mana;
    private String characterClass;
    private Equipment equipment;  // Reference type — needs deep copy!

    // empty constructor
    public GameCharacter() {}

    // Copy constructor — deep copy everything
    public GameCharacter(GameCharacter other) {
        this.name = other.name;
        this.level = other.level;
        this.health = other.health;
        this.mana = other.mana;
        this.characterClass = other.characterClass;
        // Deep copy: new Equipment object, not shared reference
        this.equipment = new Equipment(other.equipment);
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public String getCharacterClass() { return characterClass; }
    public void setCharacterClass(String characterClass) { this.characterClass = characterClass; }
    public Equipment getEquipment() { return equipment; }
    public void setEquipment(Equipment equipment) { this.equipment = equipment; }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", health=" + health +
                ", mana=" + mana +
                ", class='" + characterClass + '\'' +
                ", equipment=" + equipment +
                '}';
    }
}