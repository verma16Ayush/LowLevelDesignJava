package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.state;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderState;

public class PreparedState implements OrderState {
    @Override
    public void prepare(Order order) throws Exception {
        throw new Exception(order + " already prepared");
    }
    @Override
    public void cancel(Order order) throws Exception {
        throw new Exception(order + " already prepared");
    }
}
