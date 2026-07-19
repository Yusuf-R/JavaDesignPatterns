package com.naviroq.patterns.structural.decorator.coffeshop.extras;

import com.naviroq.patterns.structural.decorator.coffeshop.coffe.Coffee;

public class SugarDecorator extends CoffeeDecorator {

    // constructor
    public SugarDecorator (Coffee coffee ) {
        super(coffee);
    }

    @Override
    public double getCost() {
        // Go run the parents which is the CoffeeDecorator and  getCost() code!
        return super.getCost() + 0.20;
    }

    @Override
    public String getDescription() {
        // means: "Go run the parents which is the CoffeeDecorator and  getDescription() code!"
        return super.getDescription() + " + Sugar ($0.20)";
    }
}
