package com.avr.problems.parkinglot.gate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.avr.problems.parkinglot.gate.abstractions.Gate;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.ticket.Ticket;

import lombok.Getter;

@Getter
public class ExitGate implements Gate {
    String gateId;
    GateType gateType;

    public ExitGate(GateType gateType) {
        this.gateId = UUID.randomUUID().toString().substring(0, 5);
        this.gateType = gateType;
    }

    public  BigDecimal calculatePrice(Ticket ticket, ChargingStrategy chargingStrategy) {
        ticket.setExitTime(Instant.now());
        return  chargingStrategy.calculatePrice(ticket);
    }

    public  void vacateSpot(Ticket ticket) {
        ticket.getParkingSpot().removeVehicle();
    }
}
