package com.avr.problems.parkinglot.vehicle;

import java.util.List;

import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter 
public class CommercialFourWheelerDieselHeavy implements Vehicle {
    String registrationNumber;
    List<VehicleType> vehicleTypes; 

   public CommercialFourWheelerDieselHeavy(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.vehicleTypes = List.of(VehicleType.FOUR_WHEELER, VehicleType.DIESEL, VehicleType.COMMERCIAL, VehicleType.HEAVY);
    }
}
