package com.avr.problems.parkinglot.parkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.exceptions.UnsupportedVehicleException;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.vehicle.VehicleType;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data 
public class CustomParkingSpot implements ParkingSpot {

    String id;
    List<VehicleType> supportedVehicleTypes;

    @Setter(AccessLevel.NONE)
    boolean isAvailable;

    ChargingStrategy chargingStrategy;
    
    @Setter(AccessLevel.NONE)
    Vehicle occupiedBy;

    int distanceFromExit;
    int distanceFromEntrance;
    int floor;

    Lock lock = new ReentrantLock();
    Condition isOccupied = lock.newCondition();
    Condition isVacant = lock.newCondition();

    public CustomParkingSpot(List<VehicleType> vehicleTypes, ChargingStrategy chargingStrategy, int distanceFromExit, int distanceFromEntrance) {
        this.id = UUID.randomUUID().toString().substring(0, 5);
        this.supportedVehicleTypes = new ArrayList<>(vehicleTypes);
        this.chargingStrategy = chargingStrategy;
        this.distanceFromEntrance = distanceFromEntrance;
        this.distanceFromExit = distanceFromExit;
        this.isAvailable = true;
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
        return (vehicleMask & spotMask) == vehicleMask;
    }

    

    public boolean reserveSpot(Vehicle vehicle) throws UnsupportedVehicleException, InterruptedException {
        if(!isVehicleSupported(vehicle)) {
            throw new UnsupportedVehicleException();
        }
        if(lock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                while(!this.isAvailable) {
                    isVacant.await();
                }
                this.setIsAvailable(false);
                this.occupiedBy = vehicle;
                isOccupied.signalAll();
                return true;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("timed out locking spot");
            return false;
        }
    }

    public void removeVehicle() {
        lock.lock();
        try {
            if(this.getOccupyingVehicle() == null && this.isAvailable) {
                isVacant.signalAll();
                throw new UnsupportedOperationException("spot already vacant");
            }
            this.occupiedBy = null;
            this.setIsAvailable(true);
            isVacant.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Vehicle getOccupyingVehicle() {
        return this.occupiedBy;
    }
}
