package com.avr.designpatterns.structural.decorator;

import com.avr.designpatterns.structural.decorator.Implementations.MilkCoffeeDecorator;
import com.avr.designpatterns.structural.decorator.Implementations.SimpleCoffee;
import com.avr.designpatterns.structural.decorator.Implementations.SugarCoffeeDecorator;
import com.avr.designpatterns.structural.decorator.abstractions.ICoffee;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        ICoffee coffee = new SimpleCoffee();
        coffee = new MilkCoffeeDecorator(coffee);
        System.out.printf("description: %s | cost: %.2f%n", coffee.getDescription(), coffee.getCost());
        coffee = new SugarCoffeeDecorator(coffee);
        System.out.printf("description: %s | cost: %.2f%n", coffee.getDescription(), coffee.getCost());
    }
}
