package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.commands.PrepareOrder;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.state.NewOrderState;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Kitchen;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderCommand;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Waiter;

public class RealWaiter implements Waiter {

    List<OrderCommand> inQueueOrders;
    Stack<OrderCommand> history;
    Kitchen kitchen;

    public RealWaiter(Kitchen kitchen) {
        this.inQueueOrders = new ArrayList<>();
        this.history = new Stack<>();
        this.kitchen = kitchen;
    }

    @Override
    public void submitOrder() {
        for(OrderCommand order : inQueueOrders) {
            order.execute();
            history.add(order);    
        }
        inQueueOrders.clear();
    }

    @Override
    public void takeOrder(String dish) {
        Order order = new OrderImpl(new NewOrderState(), dish);
        inQueueOrders.add(new PrepareOrder(this.kitchen, order));
    }

    @Override
    public void undoLast() {
        if(history.isEmpty())
            return;
        history.pop().undo();;
    }    
}
