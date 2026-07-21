package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.commands;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Kitchen;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderCommand;

public class PrepareOrder implements OrderCommand {
    Kitchen kitchen;
    Order order;

    public PrepareOrder(Kitchen kitchen, Order order) {
        this.kitchen = kitchen;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("attempting to execute prepare command");
        kitchen.prepareDish(order);
    }

    @Override
    public void undo() {
        System.out.println("attempting to undo prepare command");
        kitchen.cancelDish(order);
    }
}
