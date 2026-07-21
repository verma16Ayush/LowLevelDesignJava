package com.avr.designpatterns.structural.decorator.textview.implementations;

import com.avr.designpatterns.structural.decorator.textview.abstractions.TextView;

public class PlainTextView implements TextView{
    String text;
    public PlainTextView(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println(text);
    }

}
