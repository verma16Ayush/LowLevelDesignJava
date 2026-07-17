package com.avr.designpatterns.structural.decorator.Implementations;

import com.avr.designpatterns.structural.decorator.abstractions.AbstractCoffeeDecorator;
import com.avr.designpatterns.structural.decorator.abstractions.ICoffee;

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
