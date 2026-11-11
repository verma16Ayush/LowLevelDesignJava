package com.avr.problems.parkinglot.vehicle.abstractions;

import java.util.List;

import com.avr.problems.parkinglot.vehicle.VehicleType;

public interface Vehicle {
    List<VehicleType> getVehicleTypes();
    String getRegistrationNumber();
}
