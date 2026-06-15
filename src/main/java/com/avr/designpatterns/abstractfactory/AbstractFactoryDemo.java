package com.avr.designpatterns.abstractfactory;

import com.avr.designpatterns.abstractfactory.Implementations.MacOsGUIFactory;
import com.avr.designpatterns.abstractfactory.Implementations.WindowsGUIFactory;
import com.avr.designpatterns.abstractfactory.abstractions.AbstractGUIFactory;

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
