package com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeColor;

public class LightColor implements ThemeColor {
    @Override
    public void apply() {
        System.out.println("this is light theme");
    }
    
}
