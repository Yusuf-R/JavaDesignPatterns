// src/com/naviroq/patterns/behavioural/template/beverage/BlackCoffee.java
package com.naviroq.patterns.behavioural.template.beverage;

public class BlackCoffee extends Beverage {

    @Override
    protected void brew() {
        System.out.println("  ☕ Dripping dark roast through filter");
    }

    @Override
    protected void addCondiments() {
        // Never called — hook returns false
    }

    // Hook override — skips condiments entirely
    @Override
    protected boolean customerWantsCondiments() {
        return false;
    }
}