package com.avr.designpatterns.abstractfactory.Implementations;

import com.avr.designpatterns.abstractfactory.abstractions.ICheckBox;

public class WindowsCheckBox implements ICheckBox {
    @Override
    public void draw() {
        System.out.println("rendered a Windows CheckBox");
    }
    
}
