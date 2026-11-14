package com.avr.problems.parkinglot.floor;

import com.avr.problems.parkinglot.floor.abstractions.Floor;
import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of the Floor interface.
 * Manages a collection of parking spots on a single floor.
 */
public class SimpleFloor implements Floor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public SimpleFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
    }

    @Override
    public List<ParkingSpot> getParkingSpots() {
        return new ArrayList<>(parkingSpots);
    }

    @Override
    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots.clear();
        this.parkingSpots.addAll(parkingSpots);
    }

    @Override
    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    @Override
    public void removeParkingSpot(String parkingSpotId) {
        parkingSpots.removeIf(spot -> {
            if (spot instanceof com.avr.problems.parkinglot.parkingSpot.CustomParkingSpot customSpot) {
                return customSpot.getId().equals(parkingSpotId);
            }
            return false;
        });
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}