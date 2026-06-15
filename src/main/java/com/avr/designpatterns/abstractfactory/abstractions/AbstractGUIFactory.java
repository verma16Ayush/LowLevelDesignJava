package com.avr.designpatterns.abstractfactory.abstractions;

public interface AbstractGUIFactory {
    IButton createButton();
    ICheckBox createCheckBox();
}
