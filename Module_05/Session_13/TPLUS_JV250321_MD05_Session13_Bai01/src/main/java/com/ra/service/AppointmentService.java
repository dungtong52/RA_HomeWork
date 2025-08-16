package com.ra.service;

import com.ra.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();

    void save(Appointment appointment);

    boolean isDoctorBusy(Long doctorId, LocalDate date, Integer hour);

    void delete(Long id);
}
