// src/com/naviroq/patterns/behavioural/template/beverage/Beverage.java
package com.naviroq.patterns.behavioural.template.beverage;

public abstract class Beverage {

    // Template method — algorithm skeleton, cannot be overridden
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
        serve();
    }

    // Concrete steps — shared by all beverages
    private void boilWater() {
        System.out.println("  Boiling water (100°C)");
    }

    private void pourInCup() {
        System.out.println("  Pouring into ceramic cup");
    }

    private void serve() {
        System.out.println("  ☕ Served to customer\n");
    }

    // Abstract steps — subclasses MUST implement
    protected abstract void brew();

    protected abstract void addCondiments();

    // Hook — subclasses CAN override, defaults to true
    protected boolean customerWantsCondiments() {
        return true;
    }
}