package com.avr.problems.parkinglot.exceptions;

public class UnsupportedVehicleException extends Exception {
    public UnsupportedVehicleException() {
        super("vehicle type not supported");
    }
}
