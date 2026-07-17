package com.avr.problems.parkinglot.Implementations;

import com.avr.problems.parkinglot.Abstractions.IParkingSpot;
import com.avr.problems.parkinglot.Abstractions.Vehicle;

public class TwoWheelerParkingSpot implements IParkingSpot {
    private int id;
    private boolean isEmpty;
    private Vehicle vehicle;


    public TwoWheelerParkingSpot(int id) {
        this.id = id;
        this.isEmpty = Boolean.TRUE;
        this.vehicle = null;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isEmpty() {
        return this.isEmpty;
    }

    @Override
    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = Boolean.FALSE;
    }

    @Override
    public void removeVehicle() {
        if(this.vehicle != null) {
            this.vehicle = null;
        }
        this.isEmpty = Boolean.TRUE;
    }
    
    @Override
    public int getPrice() {
        return 10;
    }
}
