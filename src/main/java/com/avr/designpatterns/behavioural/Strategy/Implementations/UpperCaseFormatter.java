package com.avr.designpatterns.behavioural.Strategy.Implementations;

import com.avr.designpatterns.behavioural.Strategy.Abstractions.TextFormatter;

public class UpperCaseFormatter implements TextFormatter {
    @Override
    public String format(String s) {
        System.out.println("test has been formatted to upper case");
        return s.toUpperCase();
    }
}
