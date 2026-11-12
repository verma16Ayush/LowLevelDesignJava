package com.avr.problems.parkinglot.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.strategy.abstractions.ParkingSpotFindingStrategy;

public class NearEntranceSpotFindingStrategy implements ParkingSpotFindingStrategy {
    @Override
    public ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpots) {
        Comparator<ParkingSpot> cmp = (a, b) -> Integer.compare(a.getDistanceFromEntrance(), b.getDistanceFromEntrance());
        Collections.sort(parkingSpots, cmp);
        return parkingSpots.stream().filter(p -> p.isAvailable()).findFirst().orElseGet(null);
    }
}
