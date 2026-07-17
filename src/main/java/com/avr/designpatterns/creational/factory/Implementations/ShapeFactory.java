package com.avr.designpatterns.creational.factory.Implementations;

import com.avr.designpatterns.creational.factory.abstractions.IShape;

public class ShapeFactory {
    public static IShape getShape(String type) {
        switch (type) {
            case "square" -> {
                return new Square();
            }
            case "circle" -> {
                return new Circle();
            }
            default -> {
                return null;
            }
        }
    }
}
