package com.avr.designpatterns.creational.abstractfactory.GuiFactory;

import com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations.MacOsGUIFactory;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.Implementations.WindowsGUIFactory;
import com.avr.designpatterns.creational.abstractfactory.GuiFactory.abstractions.AbstractGUIFactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        String OS = "MACOS";
        AbstractGUIFactory guiFactory = null;
        if(OS.equalsIgnoreCase("MACOS")) {
            guiFactory = new MacOsGUIFactory();
        } else if(OS.equalsIgnoreCase("WINDOWS")) {
            guiFactory = new WindowsGUIFactory();
        }

        guiFactory.createButton().draw();
        guiFactory.createCheckBox().draw();
    }
}
