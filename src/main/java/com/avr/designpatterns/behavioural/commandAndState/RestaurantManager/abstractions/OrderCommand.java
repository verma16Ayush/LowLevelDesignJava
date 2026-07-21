package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions;

public interface OrderCommand {
    void execute();
    void undo();
}
