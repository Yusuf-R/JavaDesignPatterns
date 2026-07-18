package com.naviroq.patterns.creational.prototype.monsters;

public class Appearance {
    private String color;
    private String size;
    private String texture;

    public Appearance(String color, String size, String texture) {
        this.color = color;
        this.size = size;
        this.texture = texture;
    }

    // --- COPY CONSTRUCTOR (Deep Copy) ---
    public Appearance(Appearance other) {
        this.color = other.color;
        this.size = other.size;
        this.texture = other.texture;
    }

    // Getters & Setters
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getTexture() { return texture; }
    public void setTexture(String texture) { this.texture = texture; }

    @Override
    public String toString() {
        return "Appearance{color='" + color + "', size='" + size + "', texture='" + texture + "'}";
    }
}