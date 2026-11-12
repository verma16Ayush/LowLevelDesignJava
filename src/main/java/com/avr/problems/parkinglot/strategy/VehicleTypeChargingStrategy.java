package com.avr.problems.parkinglot.strategy;

import java.math.BigDecimal;

import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.ticket.Ticket;
import com.avr.problems.parkinglot.vehicle.VehicleType;

public class VehicleTypeChargingStrategy implements ChargingStrategy {
    @Override
    public BigDecimal calculatePrice(Ticket ticket) {
        int typeMask = 0;
        for(VehicleType type : ticket.getVehicle().getVehicleTypes()) {
            typeMask = typeMask | type.getCode();
        }
        return BigDecimal.valueOf(Integer.bitCount(typeMask) * 10);
    }
}
