package com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.ICheckBox;

public class MacOsCheckBox implements ICheckBox {
    @Override
    public void draw() {
        System.out.println("rendered a MAC OS CheckBox");
    }
}
