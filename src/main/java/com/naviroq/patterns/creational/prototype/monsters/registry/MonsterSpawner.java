package com.naviroq.patterns.creational.prototype.monsters.registry;

import com.naviroq.patterns.creational.prototype.monsters.Monster;

import java.util.HashMap;
import java.util.Map;

public class MonsterSpawner {
    private final Map<String, Monster> prototypes = new HashMap<>();

    // Register a monster prototype
    public void register(String key, Monster prototype) {
        prototypes.put(key, prototype);
        System.out.println("🧬 Registered prototype: " + key);
    }

    // Unregister a monster type
    public void unregister(String key) {
        prototypes.remove(key);
        System.out.println("🗑️  Unregistered: " + key);
    }

    // Spawn a clone (deep copy)
    public Monster spawn(String key) {
        Monster prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("❌ No monster registered as: " + key);
        }
        Monster clone = prototype.clonePrototype();
        System.out.println("👾 Spawned clone: " + clone.getId());
        return clone;
    }

    // Spawn a clone and apply modifications
    public Monster spawnWithMods(String key, int healthBonus, int speedBonus, String customName) {
        Monster clone = spawn(key);
        clone.setName(customName);
        clone.getStats().setHealth(clone.getStats().getHealth() + healthBonus);
        clone.getStats().setSpeed(clone.getStats().getSpeed() + speedBonus);
        return clone;
    }

    public void showRegistry() {
        System.out.println("\n📋 Registered Prototypes: " + prototypes.keySet());
    }
}