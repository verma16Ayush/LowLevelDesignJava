package com.avr.problems.parkinglot.floor.abstractions;

import java.util.List;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;

public interface Floor {
    List<ParkingSpot> getParkingSpots();
    void setParkingSpots(List<ParkingSpot> parkingSpots);
    void addParkingSpot(ParkingSpot parkingSpot);
    void removeParkingSpot(String parkingSpotId);
}
