package com.avr.designpatterns.decorator;

import com.avr.designpatterns.decorator.abstractions.AbstractCoffeeDecorator;
import com.avr.designpatterns.decorator.abstractions.ICoffee;

public class SugarCoffeeDecorator extends AbstractCoffeeDecorator {

    SugarCoffeeDecorator(ICoffee coffee) {
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
