package com.avr.designpatterns.structural.decorator.textview.implementations;

import com.avr.designpatterns.structural.decorator.textview.abstractions.TextView;
import com.avr.designpatterns.structural.decorator.textview.abstractions.TextViewDecorator;

public class ItalicsTextDecor extends TextViewDecorator {
    public ItalicsTextDecor(TextView textView) {
        super(textView);
    }

    @Override
    public void render() {
        System.out.println("<i>");
        inner.render();
        System.out.println("<\\i>");
    }
}
