package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions;

public interface OrderState {
    void cancel(Order order) throws Exception;
    void prepare(Order order) throws Exception;
}
