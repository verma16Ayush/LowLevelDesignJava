package com.avr.designpatterns.creational.factory;
import com.avr.designpatterns.creational.factory.Implementations.ShapeFactory;
import com.avr.designpatterns.creational.factory.abstractions.IShape;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        // Create a square using the factory
        IShape square = ShapeFactory.getShape("square");
        if (square != null) {
            square.draw();
        } else {
            System.out.println("Invalid shape type: square");
        }

        // Create a circle using the factory
        IShape circle = ShapeFactory.getShape("circle");
        if (circle != null) {
            circle.draw();
        } else {
            System.out.println("Invalid shape type: circle");
        }

        // Attempt to create an invalid shape
        IShape triangle = ShapeFactory.getShape("triangle");
        if (triangle != null) {
            triangle.draw();
        } else {
            System.out.println("Invalid shape type: triangle");
        }
    }
}