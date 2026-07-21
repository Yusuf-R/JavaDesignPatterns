// src/com/naviroq/patterns/behavioural/template/beverage/Coffee.java
package com.naviroq.patterns.behavioural.template.beverage;

public class Coffee extends Beverage {

    @Override
    protected void brew() {
        System.out.println("  ☕ Dripping arabica through filter");
    }

    @Override
    protected void addCondiments() {
        System.out.println("  🥛 Adding steamed milk and sugar");
    }
}