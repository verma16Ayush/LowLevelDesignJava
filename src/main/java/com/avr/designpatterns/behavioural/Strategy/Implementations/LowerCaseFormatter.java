package com.avr.designpatterns.behavioural.Strategy.Implementations;

import com.avr.designpatterns.behavioural.Strategy.Abstractions.TextFormatter;

public class LowerCaseFormatter implements TextFormatter {

    @Override
    public String format(String s) {
        System.out.println("text has been formatted to lowerCase");
        return s.toLowerCase();
    }
    
}
