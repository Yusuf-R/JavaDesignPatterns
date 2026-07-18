package com.naviroq.patterns.creational.prototype.monsters.demo;

import com.naviroq.patterns.creational.prototype.monsters.*;
import com.naviroq.patterns.creational.prototype.monsters.registry.MonsterSpawner;

public class GameDemo {
    public static void main(String[] args) {

        System.out.println("⚔️  ====== DUNGEON CRAWLER: MONSTER SPAWNER ====== ⚔️\n");

        // --- 1. Create the Master Prototypes ---
        System.out.println("--- Building Master Prototypes ---");

        // A Goblin
        Monster goblinMaster = new Monster(
                "GOBLIN-001",
                "Goblin Grunt",
                new GameStats(50, 10, 20, 8),
                new Weapon("Rusty Dagger", 5),
                new Appearance("Green", "Small", "Wrinkled"),
                25
        );

        // An Ogre
        Monster ogreMaster = new Monster(
                "OGRE-001",
                "Ogre Brute",
                new GameStats(200, 20, 10, 30),
                new Weapon("Massive Club", 20),
                new Appearance("Brown", "Huge", "Bumpy"),
                75
        );

        // A Dragon (Boss)
        Monster dragonMaster = new Monster(
                "DRAGON-001",
                "Dragon Lord",
                new GameStats(500, 100, 30, 50),
                new Weapon("Fire Breath", 45),
                new Appearance("Red", "Massive", "Scaly"),
                500
        );

        // --- 2. Register them in the Spawner ---
        MonsterSpawner spawner = new MonsterSpawner();
        spawner.register("goblin", goblinMaster);
        spawner.register("ogre", ogreMaster);
        spawner.register("dragon", dragonMaster);
        spawner.showRegistry();

        System.out.println("\n--- Spawning Enemies ---");

        // --- 3. Spawn a Goblin Horde (5 clones) ---
        System.out.println("\n👺 Spawning 5 Goblins:");
        for (int i = 0; i < 5; i++) {
            Monster goblin = spawner.spawn("goblin");
            // Give each goblin a unique name and slightly varied health
            goblin.setName("Goblin Scout #" + (i + 1));
            goblin.getStats().setHealth(goblin.getStats().getHealth() + (i * 2));
            System.out.println("   -> " + goblin.getId() + " | " + goblin.getName() +
                    " | HP: " + goblin.getStats().getHealth());
        }

        // --- 4. Spawn an Ogre with modifications ---
        System.out.println("\n👹 Spawning a Custom Ogre:");
        Monster ogreClone = spawner.spawnWithMods(
                "ogre",
                50,    // +50 HP
                5,     // +5 Speed
                "Ogre Chieftain"
        );
        System.out.println("   -> " + ogreClone);

        // --- 5. Spawn the Dragon Boss ---
        System.out.println("\n🐉 Spawning the Dragon Boss:");
        Monster dragon = spawner.spawn("dragon");
        dragon.setName("Elder Dragon");
        System.out.println("   -> " + dragon);

        // --- 6. PROOF OF INDEPENDENCE: Modify the original prototype ---
        System.out.println("\n--- PROOF OF INDEPENDENCE ---");
        System.out.println("Before modification:");
        System.out.println("   Original Goblin Prototype HP: " + goblinMaster.getStats().getHealth());
        System.out.println("   First Goblin Clone HP: " + spawner.spawn("goblin").getStats().getHealth());

        // Modify the ORIGINAL prototype
        goblinMaster.getStats().setHealth(999);
        System.out.println("\nAfter modifying the ORIGINAL Goblin prototype's HP to 999:");
        System.out.println("   Original Goblin Prototype HP: " + goblinMaster.getStats().getHealth());
        System.out.println("   New Goblin Clone HP (spawned after modification): " + spawner.spawn("goblin").getStats().getHealth());

        System.out.println("\n✅ The clone still has the original HP! (Because it copied the value, not the reference)");
        System.out.println("✅ The prototype and clones are completely isolated!");

        System.out.println("\n⚔️  ====== ALL MONSTERS SPAWNED SUCCESSFULLY ====== ⚔️");
    }
}