package com.avr.designpatterns.abstractfactory.Implementations;

import com.avr.designpatterns.abstractfactory.abstractions.AbstractGUIFactory;
import com.avr.designpatterns.abstractfactory.abstractions.IButton;
import com.avr.designpatterns.abstractfactory.abstractions.ICheckBox;

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
