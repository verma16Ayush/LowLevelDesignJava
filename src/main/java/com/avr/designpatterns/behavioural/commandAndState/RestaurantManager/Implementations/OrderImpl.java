package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations;

import java.util.UUID;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Order;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.OrderState;

public class OrderImpl implements Order {
    OrderState state;
    String dish;
    String id;
    public OrderImpl(OrderState state, String dish) {
        this.state = state;
        this.dish = dish;
        this.id = UUID.randomUUID().toString().substring(0, 5);
    }

    @Override
    public void cancel() {
        try {
            this.state.cancel(this);
        } catch (Exception e) {
            System.out.println("**** ERROR CANCELLING ORDER ****");
            System.out.println(e.getMessage());
            System.out.println("********************************");
        }
    }

    @Override
    public void prepare() {
        try {
            this.state.prepare(this);
        } catch (Exception e) {
            System.out.println("**** ERROR PREPPING ORDER ****");
            System.out.println(e.getMessage());
            System.out.println("********************************");

        }
    }

    @Override
    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[ ID: " + id + ", " + dish + " ]";
    }
}
