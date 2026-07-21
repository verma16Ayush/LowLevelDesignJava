package com.avr.designpatterns.structural.decorator.coffee.Implementations;

import com.avr.designpatterns.structural.decorator.coffee.abstractions.AbstractCoffeeDecorator;
import com.avr.designpatterns.structural.decorator.coffee.abstractions.ICoffee;

public class MilkCoffeeDecorator extends AbstractCoffeeDecorator {

    public MilkCoffeeDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}
