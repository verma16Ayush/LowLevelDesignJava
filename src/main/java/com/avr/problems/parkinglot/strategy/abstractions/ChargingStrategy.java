package com.avr.problems.parkinglot.strategy.abstractions;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.spi.CurrencyNameProvider;

import com.avr.problems.parkinglot.ticket.Ticket;

public interface ChargingStrategy {
    BigDecimal calculatePrice(Ticket ticket);
} 