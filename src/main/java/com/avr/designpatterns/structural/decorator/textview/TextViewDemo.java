package com.avr.designpatterns.structural.decorator.textview;

import com.avr.designpatterns.structural.decorator.textview.abstractions.TextView;
import com.avr.designpatterns.structural.decorator.textview.implementations.BoldTextDecor;
import com.avr.designpatterns.structural.decorator.textview.implementations.ItalicsTextDecor;
import com.avr.designpatterns.structural.decorator.textview.implementations.PlainTextView;

public class TextViewDemo {
    public static void main(String[] args) {
        TextView text = new PlainTextView("Ayush");
        System.out.println("PLAIN:");
        text.render();
        System.out.println();

        TextView boldTextView = new BoldTextDecor(text);
        System.out.println("BOLD:");
        boldTextView.render();
        System.out.println();

        TextView italicTextView = new ItalicsTextDecor(text);
        System.out.println("ITALIC: ");
        italicTextView.render();
        System.out.println();

        TextView boldItalic = new BoldTextDecor(italicTextView);
        System.out.println("BOLD ITALIC: ");
        boldItalic.render();
        System.out.println();
    }
}
