package com.avr.designpatterns.creational.factory.Implementations;

import com.avr.designpatterns.creational.factory.abstractions.IShape;

public class Circle implements IShape {
    @Override
    public void draw() {
        System.out.println("this shape is a circle");
    }
}
