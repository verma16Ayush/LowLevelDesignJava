package com.avr.designpatterns.creational.abstractfactory.ThemeFactory.Abstractions;

public interface ThemeFactory {
    ThemeColor createThemeColor();
    ThemeFont createThemeFont();
}
