package com.avr.problems.parkinglot.Abstractions;

import com.avr.problems.parkinglot.Enums.VehicleType;

public class Vehicle {
    int vehicleId;
    VehicleType vehicleType;

    public Vehicle(int vehicleId, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public int getVehicleId() {
        return this.vehicleId;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }
}
