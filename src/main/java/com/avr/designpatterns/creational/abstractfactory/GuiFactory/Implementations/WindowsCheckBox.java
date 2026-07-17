package com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.ICheckBox;

public class WindowsCheckBox implements ICheckBox {
    @Override
    public void draw() {
        System.out.println("rendered a Windows CheckBox");
    }
    
}
