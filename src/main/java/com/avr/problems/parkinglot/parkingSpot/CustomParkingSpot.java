package com.avr.problems.parkinglot.parkingSpot;

import java.util.List;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.exceptions.UnsupportedVehicleException;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.vehicle.VehicleType;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class CustomParkingSpot implements ParkingSpot {
    List<VehicleType> supportedVehicleTypes;
    boolean isAvailable;
    ChargingStrategy chargingStrategy;
    Vehicle occupiedBy;

    public CustomParkingSpot(List<VehicleType> vehicleTypes, ChargingStrategy chargingStrategy) {
        this.supportedVehicleTypes = List.copyOf(vehicleTypes);
        this.chargingStrategy = chargingStrategy;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addSupportedVehicleType(VehicleType vehicleType) {
        this.supportedVehicleTypes.add(vehicleType);
    }

    public boolean isVehicleSupported(Vehicle vehicle) {
        int vehicleMask = 0;
        int spotMask = 0;
        for(var type : vehicle.getVehicleTypes()) {
            vehicleMask = vehicleMask | type.getCode();
        }

        for(var type : this.getSupportedVehicleTypes()) {
            spotMask = spotMask | type.getCode();
        }
        return (vehicleMask & spotMask) == spotMask;
    }
    public boolean setVehicle(Vehicle vehicle) throws UnsupportedVehicleException {
        if(!isVehicleSupported(vehicle)) {
            throw new UnsupportedVehicleException();
        }
        if(this.isAvailable) {
            this.setIsAvailable(false);
            this.setVehicle(vehicle);
            return true;
        }
        return false;
    }

    public void removeVehicle() {
        if(this.getOccupyingVehicle() == null && !this.isAvailable) {
            throw new UnsupportedOperationException("spot already vacant");
        }
        this.setOccupiedBy(null);
        this.setIsAvailable(true);
    }

    public Vehicle getOccupyingVehicle() {
        return this.occupiedBy;
    }
}
