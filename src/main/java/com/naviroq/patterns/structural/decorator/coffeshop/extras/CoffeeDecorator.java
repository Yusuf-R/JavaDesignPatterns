package com.naviroq.patterns.structural.decorator.coffeshop.extras;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;

public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee; // HAS-A Aggregation

    // constructor
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

}
