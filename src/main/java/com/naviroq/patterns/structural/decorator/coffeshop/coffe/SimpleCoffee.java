package com.naviroq.patterns.structural.decorator.coffeshop.coffe;

public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Brewed Coffe";
    }
}
