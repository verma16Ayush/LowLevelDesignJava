package com.avr.problems.parkinglot.vehicle;

import java.util.List;

import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter 
public class CommercialFourWheelerPetrol implements Vehicle {
    String registrationNumber;
    List<VehicleType> vehicleTypes; 

    public CommercialFourWheelerPetrol(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.vehicleTypes = List.of(VehicleType.FOUR_WHEELER, VehicleType.PETROL, VehicleType.COMMERCIAL);
    }
}
