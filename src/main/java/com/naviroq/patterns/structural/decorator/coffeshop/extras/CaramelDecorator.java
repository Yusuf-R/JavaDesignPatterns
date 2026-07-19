package com.naviroq.patterns.structural.decorator.coffeshop.extras;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;

public class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.90;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Caramel Shot ($0.90)";
    }
}
