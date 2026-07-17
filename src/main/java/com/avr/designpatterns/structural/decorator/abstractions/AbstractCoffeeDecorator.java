package com.avr.designpatterns.structural.decorator.abstractions;

public abstract class AbstractCoffeeDecorator implements ICoffee {
    protected ICoffee coffee;

    public AbstractCoffeeDecorator(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
