package com.avr.designpatterns.creational.abstractfactory.ThemeFactory;

import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions.ThemeFactory;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations.DarkThemeFactory;
import com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Implementations.LightThemeFactory;

public class ThemeFactoryDemo {
    public static void main(String[] args) {
        ThemeFactory darkThemeFactory = new DarkThemeFactory();
        ThemeFactory lightThemeFactory = new LightThemeFactory();

        darkThemeFactory.createThemeColor().apply();
        darkThemeFactory.createThemeFont().render();

        lightThemeFactory.createThemeColor().apply();
        lightThemeFactory.createThemeFont().render();
    }
}
