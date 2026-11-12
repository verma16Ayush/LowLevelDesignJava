package com.avr.problems.parkinglot.parkingSpot.abstractions;

import com.avr.problems.parkinglot.exceptions.UnsupportedVehicleException;
import com.avr.problems.parkinglot.vehicle.VehicleType;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

public interface ParkingSpot {
    boolean isAvailable();
    void setIsAvailable(boolean isAvailable);
    void addSupportedVehicleType(VehicleType type);
    boolean isVehicleSupported(Vehicle vehicle);
    boolean reserveSpot(Vehicle vehicle) throws UnsupportedVehicleException, InterruptedException;
    int getDistanceFromExit();
    int getDistanceFromEntrance();
    int getFloor();
    void removeVehicle();
    Vehicle getOccupyingVehicle();
}
