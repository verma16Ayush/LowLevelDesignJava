package com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFont;

public class DarkFont implements ThemeFont {
    @Override
    public void render() {
        System.out.println("this renders dark theme font");
    }
}
