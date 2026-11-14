package com.avr.problems.parkinglot.strategy.abstractions;

import java.util.List;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

public interface ParkingSpotFindingStrategy {
    ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpots, Vehicle vehicle);
}
