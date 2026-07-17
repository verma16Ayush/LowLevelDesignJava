package com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.AbstractGUIFactory;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.IButton;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.ICheckBox;

public class MacOsGUIFactory implements AbstractGUIFactory {
    @Override
    public IButton createButton() {
        return new MacOsButton();
    }

    @Override
    public ICheckBox createCheckBox() {
        return new MacOsCheckBox();
    }
}
