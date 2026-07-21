package com.avr.designpatterns.behavioural.Strategy;

import com.avr.designpatterns.behavioural.Strategy.Abstractions.TextFormatter;
import com.avr.designpatterns.behavioural.Strategy.Implementations.LowerCaseFormatter;
import com.avr.designpatterns.behavioural.Strategy.Implementations.TitleCaseFormatter;
import com.avr.designpatterns.behavioural.Strategy.Implementations.UpperCaseFormatter;

public class StrategyDemo {
    public static void main(String[] args) {
        String text = "hello strategy pattern demo";

        // Using UpperCaseFormatter
        TextFormatter upperCaseFormatter = new UpperCaseFormatter();
        System.out.println("UpperCaseFormatter Output: " + upperCaseFormatter.format(text));

        // Using LowerCaseFormatter
        TextFormatter lowerCaseFormatter = new LowerCaseFormatter();
        System.out.println("LowerCaseFormatter Output: " + lowerCaseFormatter.format(text));

        // Using TitleCaseFormatter
        TextFormatter titleCaseFormatter = new TitleCaseFormatter();
        System.out.println("TitleCaseFormatter Output: " + titleCaseFormatter.format(text));
    }
}