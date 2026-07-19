package com.naviroq.patterns.structural.decorator.coffeshop.extras;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;

public class WhipDecorator extends CoffeeDecorator {

    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        // Go run the parents which is the CoffeeDecorator and  getCost() code!
        return super.getCost() + 0.70;
    }

    @Override
    public String getDescription() {
        // means: "Go run the parents which is the CoffeeDecorator and  getDescription() code!"
        return super.getDescription() + " + Whipped Cream ($0.70)";
    }
}
