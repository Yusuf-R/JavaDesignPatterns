package com.naviroq.patterns.creational.prototype.game.demo;

import com.naviroq.patterns.creational.prototype.game.Equipment;
import com.naviroq.patterns.creational.prototype.game.GameCharacter;

public class Main {
    public static void main(String[] args) {

        // Create original character
        GameCharacter hero = new GameCharacter();
        hero.setName("Aldric");
        hero.setLevel(50);
        hero.setHealth(1000);
        hero.setMana(500);
        hero.setCharacterClass("Paladin");
        hero.setEquipment(new Equipment("Excalibur", "Dragon Plate", "Ring of Protection"));

        System.out.println("=== ORIGINAL ===");
        System.out.println(hero);

        // Clone for boss fight — deep copy
        GameCharacter bossClone = new GameCharacter(hero);
        bossClone.setName("Aldric (Boss Version)");
        bossClone.setLevel(75);  // Buffed for boss fight
        bossClone.setHealth(5000);
        bossClone.getEquipment().setWeapon("Corrupted Excalibur");  // Dark variant

        System.out.println("\n=== BOSS CLONE ===");
        System.out.println(bossClone);

        // Verify original is untouched
        System.out.println("\n=== ORIGINAL AFTER CLONE ===");
        System.out.println(hero);

        // Proof of deep copy
        System.out.println("\n=== DEEP COPY CHECK ===");
        System.out.println("Original weapon: " + hero.getEquipment().getWeapon());
        System.out.println("Clone weapon: " + bossClone.getEquipment().getWeapon());
        System.out.println("Same Equipment object? " + (hero.getEquipment() == bossClone.getEquipment()));
    }
}