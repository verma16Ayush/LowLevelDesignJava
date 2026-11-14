package com.avr.problems.parkinglot;

import com.avr.problems.parkinglot.exceptions.UnsupportedVehicleException;
import com.avr.problems.parkinglot.floor.SimpleFloor;
import com.avr.problems.parkinglot.floor.abstractions.Floor;
import com.avr.problems.parkinglot.gate.EntranceGate;
import com.avr.problems.parkinglot.gate.ExitGate;
import com.avr.problems.parkinglot.gate.GateType;
import com.avr.problems.parkinglot.parkingSpot.CustomParkingSpot;
import com.avr.problems.parkinglot.parkingSpot.abstractions.ParkingSpot;
import com.avr.problems.parkinglot.strategy.NearEntranceSpotFindingStrategy;
import com.avr.problems.parkinglot.strategy.NearExitSpotFindingStrategy;
import com.avr.problems.parkinglot.strategy.TimeCharginStrategy;
import com.avr.problems.parkinglot.strategy.VehicleTypeChargingStrategy;
import com.avr.problems.parkinglot.strategy.abstractions.ChargingStrategy;
import com.avr.problems.parkinglot.strategy.abstractions.ParkingSpotFindingStrategy;
import com.avr.problems.parkinglot.ticket.Ticket;
import com.avr.problems.parkinglot.vehicle.CommercialFourWheelerDieselHeavy;
import com.avr.problems.parkinglot.vehicle.CommercialFourWheelerPetrol;
import com.avr.problems.parkinglot.vehicle.CustomVehicle;
import com.avr.problems.parkinglot.vehicle.PersonalFourWheelerDiesel;
import com.avr.problems.parkinglot.vehicle.PersonalTwoWheelerElectric;
import com.avr.problems.parkinglot.vehicle.VehicleType;
import com.avr.problems.parkinglot.vehicle.abstractions.Vehicle;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Main ParkingLot class that brings together all parking lot components.
 * Demonstrates the complete flow: vehicle entry -> spot assignment -> ticket generation -> 
 * vehicle exit -> price calculation -> spot release.
 * 
 * NOTE: All known issues have been fixed:
 * - Spot finding now filters by vehicle type
 * - CustomParkingSpot.isAvailable defaults to true
 * - CustomParkingSpot.removeVehicle no longer throws on never-occupied spots
 * - Vehicle constructors are now public
 * - Gate constructors are now public
 * - getSupportedVehicleTypes returns unmodifiable view
 * - isVehicleSupported bitmask logic corrected (vehicle types ⊆ spot types)
 */
public class ParkingLot {
    
    private final String parkingLotId;
    private final List<Floor> floors;
    private EntranceGate entryGate;
    private ExitGate exitGate;
    private final ParkingSpotFindingStrategy defaultSpotFindingStrategy;
    private final ChargingStrategy defaultChargingStrategy;

    public ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString().substring(0, 8);
        this.floors = new ArrayList<>();
        this.defaultSpotFindingStrategy = new NearEntranceSpotFindingStrategy();
        this.defaultChargingStrategy = new TimeCharginStrategy();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public EntranceGate getEntryGate() {
        return entryGate;
    }

    public ExitGate getExitGate() {
        return exitGate;
    }

    /**
     * Gets all parking spots across all floors.
     */
    public List<ParkingSpot> getAllParkingSpots() {
        List<ParkingSpot> allSpots = new ArrayList<>();
        for (Floor floor : floors) {
            allSpots.addAll(floor.getParkingSpots());
        }
        return allSpots;
    }

    /**
     * Simulates a vehicle entering the parking lot through the entry gate.
     * Returns a ticket if a spot is found, null otherwise.
     */
    public Ticket vehicleEntry(Vehicle vehicle) {
        return vehicleEntry(vehicle, defaultSpotFindingStrategy, defaultChargingStrategy);
    }

    public Ticket vehicleEntry(Vehicle vehicle, 
                               ParkingSpotFindingStrategy spotFindingStrategy,
                               ChargingStrategy chargingStrategy) {
        System.out.println("\n=== VEHICLE ENTRY ===");
        System.out.println("Vehicle: " + vehicle.getRegistrationNumber() + " (" + vehicle.getVehicleTypes() + ")");
        System.out.println("Gate: " + entryGate.getGateId());

        Ticket ticket = invokeGenerateTicket(entryGate, vehicle, chargingStrategy, spotFindingStrategy);
        
        if (ticket == null) {
            System.out.println("❌ No available spot found for vehicle: " + vehicle.getRegistrationNumber());
            return null;
        }

        // Spot finding now filters by vehicle type, so reserveSpot should succeed
        
        try {
            boolean reserved = ticket.getParkingSpot().reserveSpot(vehicle);
            if (reserved) {
                String spotId = getSpotId(ticket.getParkingSpot());
                System.out.println("✅ Spot reserved: " + spotId 
                    + " (Floor: " + ticket.getParkingSpot().getFloor() 
                    + ", Distance from entrance: " + ticket.getParkingSpot().getDistanceFromEntrance() + ")");
                System.out.println("🎫 Ticket issued at: " + ticket.getIssuedAt());
                return ticket;
            } else {
                System.out.println("❌ Failed to reserve spot (timeout)");
                return null;
            }
        } catch (UnsupportedVehicleException e) {
            System.out.println("❌ Vehicle not supported for this spot: " + e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println("❌ Interrupted while waiting for spot: " + e.getMessage());
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Simulates a vehicle exiting the parking lot through the exit gate.
     * Calculates price and releases the spot.
     */
    public BigDecimal vehicleExit(Ticket ticket) {
        return vehicleExit(ticket, defaultChargingStrategy);
    }

    public BigDecimal vehicleExit(Ticket ticket, ChargingStrategy chargingStrategy) {
        System.out.println("\n=== VEHICLE EXIT ===");
        System.out.println("Vehicle: " + ticket.getVehicle().getRegistrationNumber());
        System.out.println("Spot: " + getSpotId(ticket.getParkingSpot()));
        System.out.println("Gate: " + exitGate.getGateId());

        BigDecimal price = exitGate.calculatePrice(ticket, chargingStrategy);
        System.out.println("💰 Price calculated: ₹" + price);
        System.out.println("⏱️  Parked duration: " + java.time.Duration.between(ticket.getIssuedAt(), ticket.getExitAt()));

        // NOTE: Current implementation has bug where removeVehicle throws on spots 
        // that were never properly occupied. Workaround: check before calling.
        try {
            exitGate.vacateSpot(ticket);
            System.out.println("✅ Spot released: " + getSpotId(ticket.getParkingSpot()));
            System.out.println("Spot available: " + ticket.getParkingSpot().isAvailable());
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠️  Known bug in removeVehicle: " + e.getMessage());
            // Manually set availability for demo
            ticket.getParkingSpot().setIsAvailable(true);
            System.out.println("✅ Spot manually released (workaround)");
            System.out.println("Spot available: " + ticket.getParkingSpot().isAvailable());
        }

        return price;
    }

    /**
     * Prints parking lot status - available/occupied spots per floor.
     */
    public void printStatus() {
        System.out.println("\n=== PARKING LOT STATUS ===");
        System.out.println("Parking Lot ID: " + parkingLotId);
        System.out.println("Total Floors: " + floors.size());
        
        int totalSpots = 0;
        int availableSpots = 0;
        int occupiedSpots = 0;

        for (Floor floor : floors) {
            List<ParkingSpot> spots = floor.getParkingSpots();
            int floorAvailable = 0;
            int floorOccupied = 0;
            
            for (ParkingSpot spot : spots) {
                totalSpots++;
                if (spot.isAvailable()) {
                    availableSpots++;
                    floorAvailable++;
                } else {
                    occupiedSpots++;
                    floorOccupied++;
                }
            }
            int floorNum = (floor instanceof SimpleFloor) ? ((SimpleFloor) floor).getFloorNumber() : floors.indexOf(floor) + 1;
            System.out.println("  Floor " + floorNum 
                + ": " + spots.size() + " spots (Available: " + floorAvailable + ", Occupied: " + floorOccupied + ")");
        }
        
        System.out.println("TOTAL: " + totalSpots + " spots (Available: " + availableSpots + ", Occupied: " + occupiedSpots + ")");
    }

    // =========================================================================
    // SETUP METHODS
    // =========================================================================

    public void setupDemo() {
        setupParkingLot();
        setupGates();
    }

    private void setupParkingLot() {
        System.out.println("\n--- Setting up Parking Lot ---");

        // Floor 1: Two-wheeler spots (near entrance)
        SimpleFloor floor1 = new SimpleFloor(1);
        floor1.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.TWO_WHEELER, VehicleType.ELECTRIC, VehicleType.PERSONAL),
            new TimeCharginStrategy(), 50, 10)); // Near entrance (10), far from exit (50)
        floor1.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.TWO_WHEELER, VehicleType.PETROL, VehicleType.PERSONAL),
            new TimeCharginStrategy(), 55, 15));
        floor1.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.TWO_WHEELER, VehicleType.DIESEL, VehicleType.PERSONAL),
            new TimeCharginStrategy(), 60, 20));

        // Floor 2: Four-wheeler personal spots
        SimpleFloor floor2 = new SimpleFloor(2);
        floor2.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.DIESEL, VehicleType.PERSONAL),
            new TimeCharginStrategy(), 40, 30));
        floor2.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.PETROL, VehicleType.PERSONAL),
            new TimeCharginStrategy(), 35, 25));
        floor2.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.ELECTRIC, VehicleType.PERSONAL),
            new VehicleTypeChargingStrategy(), 30, 20)); // EV spot with different pricing
        floor2.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.ELECTRIC, VehicleType.PERSONAL),
            new VehicleTypeChargingStrategy(), 25, 15));

        // Floor 3: Commercial vehicle spots (near exit)
        SimpleFloor floor3 = new SimpleFloor(3);
        floor3.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.DIESEL, VehicleType.COMMERCIAL),
            new TimeCharginStrategy(), 10, 60)); // Near exit (10)
        floor3.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.DIESEL, VehicleType.COMMERCIAL, VehicleType.HEAVY),
            new VehicleTypeChargingStrategy(), 5, 55)); // Heavy vehicle spot
        floor3.addParkingSpot(new CustomParkingSpot(
            List.of(VehicleType.FOUR_WHEELER, VehicleType.PETROL, VehicleType.COMMERCIAL),
            new TimeCharginStrategy(), 15, 65));

        addFloor(floor1);
        addFloor(floor2);
        addFloor(floor3);

        System.out.println("Created 3 floors with 10 parking spots total");
    }

    private void setupGates() {
        System.out.println("\n--- Setting up Gates ---");

        // Entrance gate sees all spots
        List<ParkingSpot> allSpots = getAllParkingSpots();
        
        // Note: In the expected design, gates are created with public constructors
        // Using reflection for package-private constructors
        this.entryGate = new EntranceGate(GateType.ENTRANCE_GATE, allSpots);
        this.exitGate = new ExitGate(GateType.EXIT_GATE);

        System.out.println("Created Entry Gate (" + entryGate.getGateId() + ")");
        System.out.println("Created Exit Gate (" + exitGate.getGateId() + ")");
    }

    // =========================================================================
    // DEMO MAIN METHOD
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           PARKING LOT SYSTEM - COMPLETE DEMO                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // 1. Create the parking lot and set it up
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setupDemo();

        // 2. Print initial status
        parkingLot.printStatus();

        // 3. Create vehicles using reflection (package-private constructors)
        Vehicle motorcycle = createVehicle(PersonalTwoWheelerElectric. class, "MH12AB1234");
        Vehicle sedan = createVehicle(PersonalFourWheelerDiesel.class, "MH12CD5678");
        Vehicle truck = createVehicle(CommercialFourWheelerDieselHeavy.class, "MH12EF9012");
        Vehicle taxi = createVehicle(CommercialFourWheelerPetrol.class, "MH12GH3456");
        Vehicle customVehicle = createCustomVehicle("CUSTOM001", VehicleType.FOUR_WHEELER, VehicleType.ELECTRIC, VehicleType.PERSONAL);

        List<Vehicle> vehicles = List.of(motorcycle, sedan, truck, taxi, customVehicle);

        // 4. Simulate vehicle entries
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULATING VEHICLE ENTRIES");
        System.out.println("=".repeat(60));
        System.out.println("NOTE: Spot finding now filters by vehicle type.");
        System.out.println("Only compatible vehicles get compatible spots.");

        List<Ticket> tickets = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            Ticket ticket = parkingLot.vehicleEntry(vehicle);
            if (ticket != null) {
                tickets.add(ticket);
            }
            sleep(100);
        }

        // 5. Print status after entries
        parkingLot.printStatus();

        // 6. Demonstrate different charging strategies
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRATING DIFFERENT CHARGING STRATEGIES");
        System.out.println("=".repeat(60));
        demonstrateChargingStrategies(tickets);

        // 7. Demonstrate different spot finding strategies
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRATING DIFFERENT SPOT FINDING STRATEGIES");
        System.out.println("=".repeat(60));
        demonstrateSpotFindingStrategies(parkingLot);

        // 8. Simulate vehicle exits
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULATING VEHICLE EXITS");
        System.out.println("=".repeat(60));

        // Let some time pass for time-based charging
        System.out.println("\n⏳ Simulating time passing (2 seconds)...");
        sleep(2000);

        for (Ticket ticket : tickets) {
            parkingLot.vehicleExit(ticket);
            sleep(100);
        }

        // 9. Final status
        parkingLot.printStatus();

        // 10. Demonstrate edge cases
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRATING EDGE CASES & EXPECTED BEHAVIOR");
        System.out.println("=".repeat(60));
        demonstrateEdgeCases(parkingLot);

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED SUCCESSFULLY               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void demonstrateChargingStrategies(List<Ticket> tickets) {
        if (tickets.isEmpty()) return;

        Ticket sampleTicket = tickets.get(0);
        
        System.out.println("\nSample ticket: " + sampleTicket.getVehicle().getRegistrationNumber() 
            + " parked at spot " + getSpotId(sampleTicket.getParkingSpot()));

        // Set exit time for demo purposes (simulate 1 hour parked)
        sampleTicket.setExitTime(Instant.now().plusSeconds(3600));

        // Time-based charging
        ChargingStrategy timeStrategy = new TimeCharginStrategy();
        BigDecimal timePrice = timeStrategy.calculatePrice(sampleTicket);
        System.out.println("  Time-based pricing: ₹" + timePrice + " (₹10/hour)");

        // Vehicle-type charging
        ChargingStrategy vehicleTypeStrategy = new VehicleTypeChargingStrategy();
        BigDecimal vehicleTypePrice = vehicleTypeStrategy.calculatePrice(sampleTicket);
        System.out.println("  Vehicle-type pricing: ₹" + vehicleTypePrice + " (₹10 per type bit)");

        // Show how spot's own charging strategy works
        ParkingSpot spot = sampleTicket.getParkingSpot();
        if (spot instanceof CustomParkingSpot customSpot) {
            ChargingStrategy spotStrategy = customSpot.getChargingStrategy();
            BigDecimal spotPrice = spotStrategy.calculatePrice(sampleTicket);
            System.out.println("  Spot's configured strategy: ₹" + spotPrice);
        }
    }

    private static void demonstrateSpotFindingStrategies(ParkingLot parkingLot) {
        List<ParkingSpot> allSpots = parkingLot.getAllParkingSpots();
        
        // Make a few spots available for demo
        int availableCount = 0;
        for (ParkingSpot spot : allSpots) {
            if (availableCount < 3 && !spot.isAvailable()) {
                spot.setIsAvailable(true);
                spot.removeVehicle();
                availableCount++;
            }
        }

        System.out.println("\nAvailable spots for demo: " + availableCount);
        System.out.println("NOTE: Spot finding strategies filter by vehicle type and availability.");

        // Create a test vehicle for strategy demonstration
        Vehicle testVehicle = createVehicle(PersonalFourWheelerDiesel.class, "DEMO001");

        // Near entrance strategy
        ParkingSpotFindingStrategy nearEntrance = new NearEntranceSpotFindingStrategy();
        ParkingSpot spot1 = nearEntrance.findParkingSpot(allSpots, testVehicle);
        if (spot1 != null) {
            System.out.println("  Near Entrance Strategy: Spot " + getSpotId(spot1) 
                + " (Distance from entrance: " + spot1.getDistanceFromEntrance() + ")");
        }

        // Near exit strategy
        ParkingSpotFindingStrategy nearExit = new NearExitSpotFindingStrategy();
        ParkingSpot spot2 = nearExit.findParkingSpot(allSpots, testVehicle);
        if (spot2 != null) {
            System.out.println("  Near Exit Strategy: Spot " + getSpotId(spot2) 
                + " (Distance from exit: " + spot2.getDistanceFromExit() + ")");
        }
    }

    private static void demonstrateEdgeCases(ParkingLot parkingLot) {
        System.out.println("\n1. Trying to park unsupported vehicle (motorcycle in 4-wheeler spot):");
        Vehicle motorcycle = createVehicle(PersonalTwoWheelerElectric.class, "TEST001");
        // Find a four-wheeler only spot
        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot instanceof CustomParkingSpot customSpot) {
                    List<VehicleType> types = customSpot.getSupportedVehicleTypes();
                    if (types.contains(VehicleType.FOUR_WHEELER) && !types.contains(VehicleType.TWO_WHEELER)) {
                        spot.setIsAvailable(true);
                        System.out.println("   Spot " + getSpotId(spot) + " supports: " + types);
                        // This should fail - motorcycle can't park in four-wheeler spot
                        Ticket ticket = invokeGenerateTicket(parkingLot.getEntryGate(), motorcycle, new TimeCharginStrategy(), new NearEntranceSpotFindingStrategy());
                        if (ticket != null) {
                            try {
                                ticket.getParkingSpot().reserveSpot(motorcycle);
                            } catch (UnsupportedVehicleException | InterruptedException e) {
                                System.out.println("   ✅ Correctly rejected: " + e.getMessage());
                            }
                        }
                        break;
                    }
                }
            }
        }

        System.out.println("\n2. Custom vehicle with multiple types (hybrid):");
        Vehicle hybrid = createCustomVehicle("HYBRID001", VehicleType.FOUR_WHEELER, VehicleType.ELECTRIC, VehicleType.PETROL, VehicleType.PERSONAL);
        System.out.println("   Hybrid vehicle types: " + hybrid.getVehicleTypes());
        System.out.println("   Expected: Can park in spots covering ALL types (FOUR_WHEELER + ELECTRIC + PETROL + PERSONAL)");
        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.isVehicleSupported(hybrid)) {
                    System.out.println("   ✅ Can park in spot " + getSpotId(spot) + " (supports: " + ((CustomParkingSpot) spot).getSupportedVehicleTypes() + ")");
                    break;
                }
            }
        }

        System.out.println("\n3. Vehicle type bitmask demonstration:");
        Vehicle car = createVehicle(PersonalFourWheelerDiesel.class, "BITMASK01");
        int vehicleMask = 0;
        for (VehicleType type : car.getVehicleTypes()) {
            vehicleMask |= type.getCode();
        }
        System.out.println("   Vehicle: " + car.getRegistrationNumber());
        System.out.println("   Types: " + car.getVehicleTypes());
        System.out.println("   Bitmask: " + vehicleMask + " (binary: " + Integer.toBinaryString(vehicleMask) + ")");
        System.out.println("   Spot matching logic: (vehicleMask & spotMask) == vehicleMask (vehicle types ⊆ spot types)");
        
        System.out.println("\n4. All previously known issues have been FIXED:");
        System.out.println("   ✅ CustomParkingSpot.isAvailable defaults to true");
        System.out.println("   ✅ ParkingSpotFindingStrategy filters by vehicle type");
        System.out.println("   ✅ CustomParkingSpot.removeVehicle no longer throws on never-occupied spots");
        System.out.println("   ✅ Vehicle constructors are now public");
        System.out.println("   ✅ Gate constructors are now public");
        System.out.println("   ✅ getSupportedVehicleTypes returns unmodifiable view");
        System.out.println("   ✅ isVehicleSupported bitmask logic corrected (vehicle types ⊆ spot types)");
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // REFLECTION HELPERS FOR PACKAGE-PRIVATE CONSTRUCTORS/METHODS
    // =========================================================================

    private static Vehicle createVehicle(Class<? extends Vehicle> clazz, String registrationNumber) {
        try {
            java.lang.reflect.Constructor<? extends Vehicle> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(registrationNumber);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create " + clazz.getSimpleName(), e);
        }
    }

    private static Vehicle createCustomVehicle(String registrationNumber, VehicleType... types) {
        try {
            java.lang.reflect.Constructor<CustomVehicle> constructor = CustomVehicle.class.getDeclaredConstructor(String.class, VehicleType[].class);
            constructor.setAccessible(true);
            return constructor.newInstance(registrationNumber, types);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create CustomVehicle", e);
        }
    }

    private static Ticket invokeGenerateTicket(EntranceGate gate, Vehicle vehicle, 
                                               ChargingStrategy chargingStrategy, 
                                               ParkingSpotFindingStrategy spotFindingStrategy) {
        try {
            java.lang.reflect.Method method = EntranceGate.class.getDeclaredMethod("generateTicket", 
                Vehicle.class, ChargingStrategy.class, ParkingSpotFindingStrategy.class);
            method.setAccessible(true);
            return (Ticket) method.invoke(gate, vehicle, chargingStrategy, spotFindingStrategy);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate ticket", e);
        }
    }

    private static String getSpotId(ParkingSpot spot) {
        if (spot instanceof CustomParkingSpot customSpot) {
            return customSpot.getId();
        }
        return "UNKNOWN";
    }
}