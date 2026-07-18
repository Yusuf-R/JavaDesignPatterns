// Equipment.java — reference type that needs deep copy
package com.naviroq.patterns.creational.prototype.game;

public class Equipment {
    private String weapon;
    private String armor;
    private String accessory;

    public Equipment(String weapon, String armor, String accessory) {
        this.weapon = weapon;
        this.armor = armor;
        this.accessory = accessory;
    }

    // Copy constructor for deep copy
    public Equipment(Equipment other) {
        this.weapon = other.weapon;
        this.armor = other.armor;
        this.accessory = other.accessory;
    }

    public String getWeapon() { return weapon; }
    public void setWeapon(String weapon) { this.weapon = weapon; }
    public String getArmor() { return armor; }
    public void setArmor(String armor) { this.armor = armor; }
    public String getAccessory() { return accessory; }
    public void setAccessory(String accessory) { this.accessory = accessory; }

    @Override
    public String toString() {
        return "Equipment{weapon='" + weapon + "', armor='" + armor + "', accessory='" + accessory + "'}";
    }
}