package com.avr.problems.parkinglot.strategy.abstractions;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.spi.CurrencyNameProvider;

public interface ChargingStrategy {
    BigDecimal calculatePrice();
} 