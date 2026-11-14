package com.avr.problems.parkinglot.vehicle;

import java.util.List;

import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter 
public class PersonalFourWheelerDiesel implements Vehicle {
    String registrationNumber;
    List<VehicleType> vehicleTypes; 

    public PersonalFourWheelerDiesel(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.vehicleTypes = List.of(VehicleType.FOUR_WHEELER, VehicleType.DIESEL, VehicleType.PERSONAL);
    }
}
