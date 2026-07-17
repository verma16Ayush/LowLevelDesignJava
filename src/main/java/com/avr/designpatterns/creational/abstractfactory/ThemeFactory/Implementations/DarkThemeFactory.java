package com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeColor;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFactory;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFont;

public class DarkThemeFactory implements ThemeFactory {
    @Override
    public ThemeColor createThemeColor() {
        return new DarkColor();
    }
    @Override
    public ThemeFont createThemeFont() {
        return new DarkFont();
    }
}
