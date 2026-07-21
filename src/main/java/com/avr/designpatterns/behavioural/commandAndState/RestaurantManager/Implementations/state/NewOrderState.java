package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.state;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderState;

public class NewOrderState implements OrderState {
    @Override
    public void prepare(Order order) {
        System.out.println(order + " has been prepared");
        order.setState(new PreparedState());
    }
    @Override
    public void cancel(Order order) {
        System.out.println(order + " has been cancelled successfully");
        order.setState(new CancelledState());
    }
}
