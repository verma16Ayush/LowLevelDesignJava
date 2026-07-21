package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.state;


import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderState;

public class CancelledState implements OrderState {

    @Override
    public void cancel(Order order) throws Exception {
        throw new Exception(order + " already cancelled");
    }    
    @Override
    public void prepare(Order order) throws Exception {
        throw new Exception(order + " already cancelled");
    }
}
