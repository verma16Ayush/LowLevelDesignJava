package com.avr.designpatterns.factory.Implementations;

import com.avr.designpatterns.factory.abstractions.IShape;

public class Square implements IShape {
    @Override
    public void draw() {
        System.out.println("this shape is a square");
    }
}
