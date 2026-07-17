package com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions;

public interface AbstractGUIFactory {
    IButton createButton();
    ICheckBox createCheckBox();
}
