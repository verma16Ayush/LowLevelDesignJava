package com.avr.designpatterns.structural.decorator.textview.abstractions;

public abstract class TextViewDecorator implements TextView {
    protected TextView inner;

    public TextViewDecorator(TextView inner) {
        this.inner = inner;
    }
}
