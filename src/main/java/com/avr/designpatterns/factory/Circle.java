package com.avr.designpatterns.factory;

import com.avr.designpatterns.factory.abstractions.IShape;

public class Circle implements IShape {
    @Override
    public void draw() {
        System.out.println("this shape is a circle");
    }
}
