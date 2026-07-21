package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.commands;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Kitchen;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderCommand;

public class CancelOrder implements OrderCommand {
    Kitchen kitchen;
    Order order;

    CancelOrder(Kitchen kitchen, Order order) {
        this.kitchen = kitchen;
        this.order = order;
    }
    @Override
    public void execute() {
        System.out.println("Attempting to execute cancel command");
        kitchen.cancelDish(order);
    }
    @Override
    public void undo() {
        System.out.println("Attempting to Undo Cancel Command");
        kitchen.prepareDish(order);
    }
}
