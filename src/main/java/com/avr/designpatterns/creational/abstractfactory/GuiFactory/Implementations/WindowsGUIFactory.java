package com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.AbstractGUIFactory;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.IButton;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.ICheckBox;

public class WindowsGUIFactory implements AbstractGUIFactory {

    @Override
    public IButton createButton() {
        return new WindowsButton();
    }
    
    @Override
    public ICheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
