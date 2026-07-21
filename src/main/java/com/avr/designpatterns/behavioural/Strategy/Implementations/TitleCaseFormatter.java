package com.avr.designpatterns.behavioural.Strategy.Implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avr.designpatterns.behavioural.Strategy.Abstractions.TextFormatter;

public class TitleCaseFormatter implements TextFormatter {
    @Override
    public String format(String s) {
        System.out.println("text has been formatted to title case");
        List<StringBuilder> sb = new ArrayList<>();
        Arrays.stream(s.split(" ")).forEach(word -> sb.add(new StringBuilder(word)));
        sb.forEach(word -> {
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
        });
        StringBuilder ans = new StringBuilder();
        sb.forEach(word -> ans.append(word).append(" "));
        return ans.toString().trim();
    }
    
}
