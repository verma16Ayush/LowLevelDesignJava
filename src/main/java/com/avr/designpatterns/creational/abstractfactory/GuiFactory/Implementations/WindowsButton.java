package com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.IButton;

public class WindowsButton implements IButton {
    @Override
    public void draw() {
        System.out.println("rendered a windows button");
    }
}
