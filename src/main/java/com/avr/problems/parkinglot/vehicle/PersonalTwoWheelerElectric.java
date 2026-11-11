package com.avr.problems.parkinglot.vehicle;

import java.util.List;

import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter  
public class PersonalTwoWheelerElectric implements Vehicle{
    String registrationNumber;
    List<VehicleType> vehicleTypes; 

    PersonalTwoWheelerElectric(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.vehicleTypes = List.of(VehicleType.TWO_WHEELER, VehicleType.ELECTRIC, VehicleType.PERSONAL);
    }
}
