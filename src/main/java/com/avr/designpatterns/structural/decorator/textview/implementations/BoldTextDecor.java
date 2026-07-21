package com.avr.designpatterns.structural.decorator.textview.implementations;

import com.avr.designpatterns.structural.decorator.textview.abstractions.TextView;
import com.avr.designpatterns.structural.decorator.textview.abstractions.TextViewDecorator;

public class BoldTextDecor extends TextViewDecorator {
    
    public BoldTextDecor(TextView textView) {
        super(textView);
    }

    @Override
    public void render() {
        System.out.println("<b>");
        inner.render();
        System.out.println("<\\b>");
        
    }
}
