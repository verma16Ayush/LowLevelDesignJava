package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions;

public interface Kitchen {
    void prepareDish(Order order);
    void cancelDish(Order order);
}
