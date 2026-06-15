package com.avr.designpatterns.abstractfactory.Implementations;

import com.avr.designpatterns.abstractfactory.abstractions.ICheckBox;

public class MacOsCheckBox implements ICheckBox {
    @Override
    public void draw() {
        System.out.println("rendered a MAC OS CheckBox");
    }
}
