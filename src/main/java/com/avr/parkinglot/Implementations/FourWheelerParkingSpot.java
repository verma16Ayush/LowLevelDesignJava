package com.avr.parkinglot.Implementations;

import com.avr.parkinglot.Abstractions.IParkingSpot;
import com.avr.parkinglot.Abstractions.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FourWheelerParkingSpot implements IParkingSpot {
    int id;
    boolean isEmpty;
    Vehicle vehicle;

    public FourWheelerParkingSpot(int id) {
        this.id = id;
        this.isEmpty = Boolean.TRUE;
        this.vehicle = null;
    }

    @Override
    public void parkVehicle(Vehicle vehicle) {
        if(this.vehicle != null) {
            return;
        }
        this.setEmpty(false);
        this.setVehicle(vehicle);
    }

    @Override
    public void removeVehicle() {
        if(this.vehicle == null) {
            return;
        }
        this.setEmpty(true);
        this.setVehicle(null);
    }

    @Override
    public int getPrice() {
        return 20;
    }
}
