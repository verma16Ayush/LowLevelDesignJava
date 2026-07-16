package com.avr.designpatterns.ClassRelationships.Association;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HospitalAppointmentSystemTest {

    @Test
    void testDoctorCreationAndAppointments() {
        HospitalAppointmentSystem system = new HospitalAppointmentSystem();
        HospitalAppointmentSystem.Doctor doctor = system.new Doctor("Dr. Smith");

        assertEquals("Dr. Smith", doctor.getName());
        assertTrue(doctor.getAppointment().isEmpty());

        HospitalAppointmentSystem.Patient patient = system.new Patient("John Doe");
        HospitalAppointmentSystem.Room room = system.new Room("Room 101", "1st Floor");
        HospitalAppointmentSystem.Appointment appointment = system.new Appointment(doctor, patient, LocalDateTime.now(), room);

        doctor.addAppointment(appointment);

        List<HospitalAppointmentSystem.Appointment> appointments = doctor.getAppointment();
        assertEquals(1, appointments.size());
        assertEquals(appointment, appointments.get(0));
    }

    @Test
    void testPatientCreationAndAppointments() {
        HospitalAppointmentSystem system = new HospitalAppointmentSystem();
        HospitalAppointmentSystem.Patient patient = system.new Patient("Jane Doe");

        assertEquals("Jane Doe", patient.getName());
        assertTrue(patient.getAppointments().isEmpty());

        HospitalAppointmentSystem.Doctor doctor = system.new Doctor("Dr. Brown");
        HospitalAppointmentSystem.Room room = system.new Room("Room 202", "2nd Floor");
        HospitalAppointmentSystem.Appointment appointment = system.new Appointment(doctor, patient, LocalDateTime.now(), room);

        patient.addAppointment(appointment);

        List<HospitalAppointmentSystem.Appointment> appointments = patient.getAppointments();
        assertEquals(1, appointments.size());
        assertEquals(appointment, appointments.get(0));
    }

    @Test
    void testRoomCreation() {
        HospitalAppointmentSystem system = new HospitalAppointmentSystem();
        HospitalAppointmentSystem.Room room = system.new Room("Room 303", "3rd Floor");

        assertEquals("Room 303", room.getName());
        assertEquals("3rd Floor", room.getFloor());
    }

    @Test
    void testAppointmentCreation() {
        HospitalAppointmentSystem system = new HospitalAppointmentSystem();
        HospitalAppointmentSystem.Doctor doctor = system.new Doctor("Dr. Green");
        HospitalAppointmentSystem.Patient patient = system.new Patient("Alice");
        HospitalAppointmentSystem.Room room = system.new Room("Room 404", "4th Floor");
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 20, 10, 30);

        HospitalAppointmentSystem.Appointment appointment = system.new Appointment(doctor, patient, dateTime, room);

        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
        assertEquals(room, appointment.getRoom());
        assertEquals(dateTime, appointment.getDateTime());
    }
}