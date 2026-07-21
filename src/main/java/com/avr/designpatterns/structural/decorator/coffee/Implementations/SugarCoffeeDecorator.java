package com.avr.designpatterns.structural.decorator.coffee.Implementations;

import com.avr.designpatterns.structural.decorator.coffee.abstractions.AbstractCoffeeDecorator;
import com.avr.designpatterns.structural.decorator.coffee.abstractions.ICoffee;

public class SugarCoffeeDecorator extends AbstractCoffeeDecorator {

    public SugarCoffeeDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
