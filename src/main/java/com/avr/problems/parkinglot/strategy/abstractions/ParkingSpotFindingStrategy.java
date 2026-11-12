package com.avr.problems.parkinglot.strategy.abstractions;

import java.util.List;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;

public interface ParkingSpotFindingStrategy {
    ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpots);
}
