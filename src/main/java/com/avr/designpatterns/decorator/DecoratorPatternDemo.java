package com.avr.designpatterns.decorator;

import com.avr.designpatterns.decorator.abstractions.ICoffee;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        ICoffee coffee = new SimpleCoffee();
        coffee = new MilkCoffeeDecorator(coffee);
        System.out.printf("description: %s | cost: %.2f%n", coffee.getDescription(), coffee.getCost());
        coffee = new SugarCoffeeDecorator(coffee);
        System.out.printf("description: %s | cost: %.2f%n", coffee.getDescription(), coffee.getCost());
    }
}
