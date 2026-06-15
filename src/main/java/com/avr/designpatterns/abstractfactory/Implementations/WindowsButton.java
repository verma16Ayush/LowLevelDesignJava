package com.avr.designpatterns.abstractfactory.Implementations;

import com.avr.designpatterns.abstractfactory.abstractions.IButton;

public class WindowsButton implements IButton {
    @Override
    public void draw() {
        System.out.println("rendered a windows button");
    }
}
