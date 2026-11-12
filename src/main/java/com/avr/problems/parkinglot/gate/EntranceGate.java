package com.avr.problems.parkinglot.gate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.avr.problems.parkinglot.gate.abstractions.Gate;
import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.strategy.abstractions.ParkingSpotFindingStrategy;
import com.avr.problems.parkinglot.ticket.Ticket;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;

@Getter
public class EntranceGate implements Gate {
    String gateId;
    GateType gateType;
    List<ParkingSpot> parkingSpots;

    EntranceGate(GateType gateType, List<ParkingSpot> parkingSpots) {
        this.gateId = UUID.randomUUID().toString().substring(0, 5);
        this.gateType = gateType;
        this.parkingSpots = parkingSpots;
    }
 
    Ticket generateTicket(Vehicle vehicle, ChargingStrategy chargingStrategy, ParkingSpotFindingStrategy spotFindingStrategy) {
        ParkingSpot foundSpot = null;
        for(int i = 0; i < 5; i++) {
            foundSpot = spotFindingStrategy.findParkingSpot(this.parkingSpots);
            if(foundSpot != null)
                break;
        }
        if(foundSpot == null)
            return null;
        Ticket ticket = new Ticket(Instant.now(), foundSpot, vehicle, chargingStrategy);
        return ticket;
    }
}
