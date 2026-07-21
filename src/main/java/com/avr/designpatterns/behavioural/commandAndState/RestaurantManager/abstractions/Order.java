package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions;

public interface Order {
    void prepare();
    void cancel();
    void setState(OrderState state);
}
