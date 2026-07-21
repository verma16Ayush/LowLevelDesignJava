package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions;

public interface Waiter {
    void takeOrder(String dish);
    void submitOrder();
    void undoLast();
}
