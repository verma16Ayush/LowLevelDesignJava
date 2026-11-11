package com.avr.problems.parkinglot.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter 
public class CustomVehicle implements Vehicle{
    String registrationNumber;
    List<VehicleType> vehicleTypes;
    CustomVehicle(String registrationNumber, VehicleType... vehicleTypes) {
        this.registrationNumber = registrationNumber;
        this.vehicleTypes = new ArrayList<>();
        for(var tp : vehicleTypes) {
            this.vehicleTypes.add(tp);
        }
    }
}
