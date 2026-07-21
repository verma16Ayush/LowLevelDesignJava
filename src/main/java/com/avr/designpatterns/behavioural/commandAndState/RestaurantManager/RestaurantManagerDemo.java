package com.avr.designpatterns.behavioural.commandAndState.RestaurantManager;

import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.RealKitchen;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.Implementations.RealWaiter;
import com.avr.designpatterns.behavioural.commandAndState.RestaurantManager.abstractions.Waiter;

public class RestaurantManagerDemo {
    public static void main(String[] args) {
        Waiter waiter = new RealWaiter(new RealKitchen());

        // Test 1: Prepare a dish
        waiter.takeOrder("Pasta");
        waiter.takeOrder("Pasta1");

        waiter.undoLast();
        
        waiter.submitOrder();

        // Test 2: Undo the preparation
        waiter.undoLast();

        // Test 3: Cancel a dish
        waiter.takeOrder("Pizza");
        waiter.submitOrder();
        waiter.undoLast();
    }
}
