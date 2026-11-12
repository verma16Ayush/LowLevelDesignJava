package com.avr.problems.parkinglot.ticket;

import java.time.Instant;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter 
public class Ticket {
    Instant issuedAt;
    Instant exitAt;
    ParkingSpot parkingSpot;
    Vehicle vehicle;
    ChargingStrategy chargingStrategy;

    public Ticket(Instant issuedAt, ParkingSpot spot, Vehicle vehicle, ChargingStrategy chargingStrategy) {
        this.issuedAt = issuedAt;
        this.parkingSpot = spot;
        this.vehicle = vehicle;
        this.chargingStrategy = chargingStrategy;
    }

    public void setExitTime(Instant exInstant) {
        this.exitAt = exInstant;
    }
}
