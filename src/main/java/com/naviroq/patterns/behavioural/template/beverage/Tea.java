// src/com/naviroq/patterns/behavioural/template/beverage/Tea.java
package com.naviroq.patterns.behavioural.template.beverage;

public class Tea extends Beverage {

    @Override
    protected void brew() {
        System.out.println("  🍃 Steeping green tea bag for 3 minutes");
    }

    @Override
    protected void addCondiments() {
        System.out.println("  🍋 Adding slice of lemon");
    }
}