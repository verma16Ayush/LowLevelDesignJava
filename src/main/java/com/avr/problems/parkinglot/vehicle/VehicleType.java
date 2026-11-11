package com.avr.problems.parkinglot.vehicle;

public enum VehicleType {
    ELECTRIC(1),
    DIESEL(2),
    PETROL(4),
    TWO_WHEELER(8),
    FOUR_WHEELER(16),
    PERSONAL(32),
    COMMERCIAL(64),
    HEAVY(128);
    
    private final int code;

    VehicleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
