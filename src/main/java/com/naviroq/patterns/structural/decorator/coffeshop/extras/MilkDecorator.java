package com.naviroq.patterns.structural.decorator.coffeshop.extras;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Milk ($0.50)";
    }

}
