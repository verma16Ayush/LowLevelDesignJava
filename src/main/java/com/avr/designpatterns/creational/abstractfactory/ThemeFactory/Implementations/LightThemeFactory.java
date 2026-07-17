package com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations;

import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeColor;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFactory;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFont;

public class LightThemeFactory implements ThemeFactory {
    @Override
    public ThemeColor createThemeColor() {
        return new LightColor();
    }
    @Override
    public ThemeFont createThemeFont() {
        return new LightFont();
    }
}
