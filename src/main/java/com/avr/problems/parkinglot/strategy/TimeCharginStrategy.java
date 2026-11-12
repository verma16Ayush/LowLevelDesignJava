package com.avr.problems.parkinglot.strategy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.ticket.Ticket;

public class TimeCharginStrategy implements ChargingStrategy {
    @Override
    public BigDecimal calculatePrice(Ticket ticket) {
        long hoursParked = Duration.between(ticket.getIssuedAt(), ticket.getExitAt()).toHours();
        return BigDecimal.valueOf(hoursParked * 10);
    }
}
