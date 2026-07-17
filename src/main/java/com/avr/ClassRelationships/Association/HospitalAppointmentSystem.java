package com.avr.ClassRelationships.Association;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*
 * Let's build a system that combines multiple association types in a realistic domain. A hospital manages doctors, patients, rooms, and appointments. The relationships between these entities demonstrate unidirectional, bidirectional, one-to-many, and many-to-many associations working together.

Here's how the classes connect:

Appointment holds a reference to a Room (unidirectional, the room doesn't know about its appointments).
Doctor has a list of Appointment objects, and each Appointment points back to its Doctor (bidirectional one-to-many).
Patient has a list of Appointment objects, and each Appointment points back to its Patient (bidirectional one-to-many).
Doctor and Patient are connected many-to-many through Appointment as an intermediary. A doctor sees many patients, and a patient can visit many doctors, but they don't reference each other directly.
*/
public class HospitalAppointmentSystem {
    class Room {
        private String name;
        private String floor;
        public Room(String name, String floor) {
            this.name = name;
            this.floor = floor;
        }
        public String getName() {return this.name;}
        public String getFloor() {return this.floor;}
    }

    class Appointment {
        private Doctor doctor;
        private Patient patient;
        private LocalDateTime dateTime;
        private Room room;

        public Appointment(Doctor doctor, Patient patient, LocalDateTime dateTime, Room room) {
            this.doctor = doctor;
            this.patient = patient;
            this.dateTime = dateTime;
            this.room = room;
        }
        public Doctor getDoctor() {
            return doctor;
        }
        public LocalDateTime getDateTime() {
            return dateTime;
        }
        public Patient getPatient() {
            return patient;
        }
        public Room getRoom() {
            return room;
        }
    }

    class Doctor {
        private String name;
        private List<Appointment> appointments;

        public Doctor(String name, List<Appointment> appointments) {
            this.name = name;
            this.appointments = new ArrayList<>();
        }

        public Doctor(String name) {
            this.name = name;
            this.appointments = new ArrayList<>();
        }

        public String getName() {
            return name;
        }
        public List<Appointment> getAppointment() {
            return appointments;
        }

        public void addAppointment(Appointment appointment) {
            if(this.appointments == null) {
                this.appointments = new ArrayList<>();
            }
            this.appointments.add(appointment);
        }
    }

    class Patient {
        private String name;
        private List<Appointment> appointments;

        public Patient(String name, List<Appointment> appointments) {
            this.name = name;
            this.appointments = appointments;
        }

        public Patient(String name) {
            this.name = name;
            this.appointments = new ArrayList<>();
        }

        public List<Appointment> getAppointments() {
            return appointments;
        }
        public String getName() {
            return name;
        }
        public void addAppointment(Appointment appointment) {
            if(this.appointments == null) {
                this.appointments = new ArrayList<>();
            }
            this.appointments.add(appointment);
        }
    }

    
}
