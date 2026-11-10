# LowLevelDesignJava - Agent Guide

## Project Overview
Java educational repository demonstrating low-level design concepts: design patterns (creational, structural, behavioral), class relationships/associations, and a parking lot system implementation.

## Build & Test Commands
```bash
# Run all tests (requires Java 17-21; Java 27 has Lombok incompatibility)
GRADLE_OPTS="--enable-native-access=ALL-UNNAMED" ./gradlew test

# Build project (compile + test)
GRADLE_OPTS="--enable-native-access=ALL-UNNAMED" ./gradlew build

# Compile only
GRADLE_OPTS="--enable-native-access=ALL-UNNAMED" ./gradlew compileJava

# Run specific test class
GRADLE_OPTS="--enable-native-access=ALL-UNNAMED" ./gradlew test --tests "com.avr.ClassRelationships.Association.HospitalAppointmentSystemTest"
```

## Known Issues
- **Java 27 + Lombok 1.18.8 fails** at compile time (`NoSuchFieldException: TypeTag.UNKNOWN`). Use Java 17-21 or update Lombok to ≥1.18.32.
- Gradle wrapper is 8.14 (may auto-upgrade). Use `GRADLE_OPTS="--enable-native-access=ALL-UNNAMED"` for Java 21+.

## Project Structure
```
src/main/java/com/avr/
├── Main.java                                    # Trivial entry point (prints hello)
├── ClassRelationships/Association/              # Association examples (tested)
│   ├── HospitalAppointmentSystem.java           # Doctor/Patient/Room/Appointment
│   ├── OnlineCoursePlatform.java                # Instructor/Course/Student
│   └── SocialNetwork.java                       # User/Message
├── designpatterns/
│   ├── creational/
│   │   ├── factory/                             # Factory Pattern (Shape)
│   │   ├── builder/                             # Builder Pattern (Email)
│   │   └── abstractfactory/
│   │       ├── ThemeFactory/                    # Theme (Color/Font) + Demo
│   │       └── GuiFactory/                      # OS-specific Button/Checkbox + Demo
│   ├── structural/
│   │   └── decorator/
│   │       ├── coffee/                          # Coffee + Milk/Sugar decorators + Demo
│   │       └── textview/                        # TextView + Bold/Italic decorators + Demo
│   └── behavioural/
│       ├── Strategy/                            # TextFormatter (Upper/Lower/Title) + Demo
│       ├── Observer/                            # WeatherStation + Displays + Demo
│       └── commandAndState/RestaurantManager/   # Command + State pattern combo + Demo
└── problems/parkinglot/                         # Parking Lot problem
    ├── Abstractions/ (Vehicle, IParkingSpot)
    ├── Implementations/ (Two/FourWheelerParkingSpot)
    └── Enums/VehicleType
```

## Test Conventions
- Tests only exist for `ClassRelationships/Association` (2 test classes).
- Uses JUnit 5 (`org.junit.jupiter.api.Test`).
- Run via `./gradlew test` (uses `useJUnitPlatform()`).

## Key Patterns in Codebase
- **Demo classes** (`*Demo.java`) in each pattern package show usage — runnable via IDE.
- **Abstractions/Implementations** subpackages separate interfaces from concrete classes.
- Lombok used (`compileOnly` + `annotationProcessor`) but no `@Data`/annotations visible in source — may be residual config.

## Running Demos
No main methods in demo classes. Run via IDE (IntelliJ/VS Code) by right-clicking a `*Demo.java` and selecting Run, or add a temporary `main` to invoke demo logic.

## No CI/CD, No Linting, No Formatting Config
- Only `build.gradle` + `settings.gradle` + wrapper.
- No Checkstyle, SpotBugs, or formatter configured.
- No GitHub Actions or pre-commit hooks.