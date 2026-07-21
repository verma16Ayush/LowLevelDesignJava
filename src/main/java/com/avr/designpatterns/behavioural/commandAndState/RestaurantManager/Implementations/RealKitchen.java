package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Kitchen;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;

public class RealKitchen implements Kitchen {
    @Override
    public void prepareDish(Order order) {
        order.prepare();
    }

    @Override
    public void cancelDish(Order order) {
        order.cancel();
    }
}
