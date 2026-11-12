package com.avr.problems.parkinglot.gate.abstractions;

import com.avr.problems.parkinglot.gate.GateType;

public interface Gate {
    GateType getGateType();
    String getGateId();
}
