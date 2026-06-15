package com.avr.parkinglot.Abstractions;

public interface IParkingSpot {
    int getId();
    boolean isEmpty();
    void parkVehicle(Vehicle vehicle);
    void removeVehicle();
    int getPrice();
}
