package com.avr.designpatterns.decorator.Implementations;

import com.avr.designpatterns.decorator.abstractions.ICoffee;

public class SimpleCoffee implements ICoffee {
    
    @Override
    public String getDescription() {
        return "simple coffee";
    }

    @Override
    public double getCost() {
        return 1.0;
    }

}
